package com.bridge.androidtechnicaltest.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.Pupil
import io.reactivex.internal.subscriptions.SubscriptionHelper.cancel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_pupillist.*
import org.koin.android.viewmodel.ext.android.sharedViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class PupilListFragment : Fragment() {


    companion object {
        const val TAG = "PUPIL_LIST_FRAGMENT"
    }

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
        pupilViewModel.getErrorObservable().observe(this, Observer {
            showError(it)
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

    private fun showError(message: String) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.error_ok,
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.dismiss()
                        })
                setNegativeButton(R.string.error_cancel,
                        DialogInterface.OnClickListener { dialog, id ->
                            dialog.cancel()
                        })
            }
            builder.setTitle("error")
            builder.setMessage(message)
            builder.create()
            builder.show()
        }


    }
}