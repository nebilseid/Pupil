package com.bridge.androidtechnicaltest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bridge.androidtechnicaltest.R
import com.bridge.androidtechnicaltest.db.Pupil
import kotlinx.android.synthetic.main.list_item_pupil.view.*

class PupilsAdapter(
        val onItemClicked: (Pupil) -> Unit) : RecyclerView.Adapter<PupilsAdapter.PupilsViewHolder>() {

    private val data = arrayListOf<Pupil>()

    fun setData(items: List<Pupil>) {
        data.clear()
        data.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            PupilsViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                            R.layout.list_item_pupil,
                            parent,
                            false
                    )
            )

    override fun getItemCount() = data.size
    override fun onBindViewHolder(viewHolder: PupilsViewHolder, position: Int) {
        viewHolder.bind(data[position], onItemClicked)

    }

    class PupilsViewHolder(var view: View) : RecyclerView.ViewHolder(view) {
        fun bind(response: Pupil, onItemClicked: (Pupil) -> Unit) {
            with(itemView) {
                view.tv_pupil_lname.text = response.name
                view.tv_pupil_country.text = response.country

                setOnClickListener {
                    onItemClicked.invoke(response)
                }


            }


        }
    }
}