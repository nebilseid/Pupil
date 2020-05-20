package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.Pupil
import kotlinx.android.synthetic.main.fragment_pupildetail.*
import kotlinx.android.synthetic.main.fragment_pupillist.*
import kotlinx.android.synthetic.main.list_item_pupil.*

class PupilDetailFragment : Fragment() {

    companion object {
        const val TAG = "PupilDetailFragment"
        fun newInstance(pupil: Pupil) = PupilDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable(NavigateArguments.PUPIL_DETAILS, pupil)
            }
        }
    }

    private val pupil by lazy {
        arguments?.getParcelable<Pupil>(NavigateArguments.PUPIL_DETAILS)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_pupildetail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pupil?.let {
            pupil_name.text = it.name
            pupil_id.text = it.pupilId.toString()
        }
    }
}