package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bridge.androidtechnicaltest.R
import kotlinx.android.synthetic.main.fragment_pupillist.*
import org.koin.android.viewmodel.ext.android.viewModel

class PupilListFragment : Fragment() {

    private val pupilViewModel : PupilViewModel by viewModel()
    private val pupilsAdapter = PupilsAdapter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_pupillist, container, false)
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getPupils()
        initializeView()
    }

    fun getPupils() {
        pupilViewModel.getPupils()
        pupilViewModel.pupilViewModel
                .observe(this, Observer {
            pupilsAdapter.setData(it.items)
        })
    }


    fun initializeView() {
        val layoutManager = LinearLayoutManager(activity)
        rv_pupil_list.layoutManager = layoutManager
        rv_pupil_list.adapter = pupilsAdapter
    }
}