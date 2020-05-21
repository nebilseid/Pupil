package com.bridge.androidtechnicaltest.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.Pupil
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_add_pupil.*
import kotlinx.android.synthetic.main.fragment_pupillist.*
import org.koin.android.ext.android.inject


class PupilAddFragment : Fragment() {


    private val pupilAddViewModel: PupilAddViewModel by inject()
    private val pupilViewModel: PupilViewModel by inject()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_pupil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lat: TextInputEditText = view.findViewById(R.id.tiet_pupil_latitude)
        bt_login_guest_continue.setOnClickListener {
            val pupil = Pupil(
                    pupilId = 0,
                    name = tiet_pupil_name.toString(),
                    country = tiet_country.toString(),
                    image = tiet_pupil_image_url.toString(),
                    latitude =  33.4,
                    longitude = 23.3)


            pupilAddViewModel.getLoadingObservable().observe(this, Observer {
                loading(it)
            })
            pupilAddViewModel.getPupilsContentObservable().observe(this, Observer {
                    //TODO
            })
            pupilAddViewModel.getErrorObservable().observe(this, Observer {
                showError(it)
            })
            pupilAddViewModel.addPupil(pupil)
        }

    }

    private fun loading(isInProgress: Boolean) {
        pb_pupil_list.visibility = if (isInProgress) View.VISIBLE else View.GONE
    }

    private fun showError(message: String) {
        val alertDialog: AlertDialog? = activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(R.string.error_ok,
                        DialogInterface.OnClickListener { dialog, id -> dialog.dismiss()
                        })
                setNegativeButton(R.string.error_cancel,
                        DialogInterface.OnClickListener { dialog, id -> dialog.cancel()
                        })
            }
            builder.setTitle("error")
            builder.setMessage(message)
            builder.create()
            builder.show()
        }
    }
}