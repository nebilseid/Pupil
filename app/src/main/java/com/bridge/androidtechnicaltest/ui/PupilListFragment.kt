package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.Pupil
import kotlinx.android.synthetic.main.fragment_pupillist.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PupilListFragment : Fragment() {


    private val onItemClicked: ((Pupil) -> Unit) = {
        it.let {
            mainActivityViewModel.goto(MainActivityViewModel.Navigation.ToPupilDetails(pupil = it))
        }
    }
    private val mainActivityViewModel: MainActivityViewModel by sharedViewModel()
    private val pupilViewModel: PupilViewModel by viewModel()
    private val pupilsAdapter = PupilsAdapter(onItemClicked)


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pupillist, container, false)
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeView()

        pupilViewModel.getLoadingObservable().observe(this, Observer {
            loading(it)
        })
        pupilViewModel.getPupilsContentObservable().observe(this, Observer {
            pupilsAdapter.setData(it.items)
        })
        pupilViewModel.getPupils()
    }


    private fun initializeView() {
        val layoutManager = LinearLayoutManager(activity)
        rv_pupil_list.layoutManager = layoutManager
        rv_pupil_list.adapter = pupilsAdapter
    }
    private fun loading(isInProgress: Boolean) {
        pb_pupil_list.visibility = if (isInProgress) View.VISIBLE else View.GONE
    }
}