package com.bridge.androidtechnicaltest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.Pupil
import com.bridge.androidtechnicaltest.ui.extensions.NavigateArguments
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_pupildetail.*

class PupilAddFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_add_pupil, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}