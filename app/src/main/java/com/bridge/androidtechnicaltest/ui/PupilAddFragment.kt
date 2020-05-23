package com.bridge.androidtechnicaltest.ui

import android.content.Context
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
import org.koin.android.ext.android.inject

class PupilAddFragment : Fragment() {

    companion object {
        const val TAG = "ADD_PUPIL_FRAGMENT"
    }


    private val pupilAddViewModel: PupilAddViewModel by inject()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_pupil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val lat: TextInputEditText = view.findViewById(R.id.tiet_pupil_latitude)
        pb_pupil_add.visibility = View.GONE

        bt_add_pupil.setOnClickListener {
            val img_url = til_pupil_image_url.editText?.text.toString()
            if (img_url.length < 12) {
                tiet_pupil_image_url.error = getString(R.string.image_url_length_error)
            } else {
                val pupil = Pupil(
                        pupilId = 0,
                        name = til_pupil_name.editText?.text.toString(),
                        country = til_country.editText?.text.toString(),
                        image = img_url,
                        latitude = til_pupil_latitude.editText?.text.toString().toDouble(),
                        longitude = til_pupil_longitude.editText?.text.toString().toDouble())

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
    }

    private fun loading(isLoading: Boolean) {
        pb_pupil_add.visibility = if (isLoading) View.VISIBLE else View.GONE
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