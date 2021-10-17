package com.example.menu_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row_top100.view.*

class RVAdapter_top100(private val names: List<Apps100>) : RecyclerView.Adapter<RVAdapter_top100.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row_top100, parent, false
            )
        )

    }


    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val name = names[position]


        holder.itemView.apply {
            tltle.text = "${name.title}\n"
            // cvCard.setOnClickListener { CustomAlertDialog("${name.title}", "${name.link}") }

        }

    }


    override fun getItemCount() = names.size

}
