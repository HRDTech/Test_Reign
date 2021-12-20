package com.solucioneshr.soft.testreign.ui

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.solucioneshr.soft.testreign.R
import com.solucioneshr.soft.testreign.data.Hit
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.time.Duration
import java.time.LocalDateTime
import java.util.*
import kotlin.time.toDuration

class Items_RVAdapter (private val itemList: MutableList<Hit>, val itemClickListener: OnItemClickListener)  :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val viewItemType = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == viewItemType) {
            val view =
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
            ItemViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.progress_loading, parent, false)
            LoadingViewHolder(view)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return viewItemType
    }

    private class ItemViewHolder (@NonNull itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var titleItem: TextView = itemView.findViewById(R.id.textItemTitle)
        var bodyItem: TextView = itemView.findViewById(R.id.textItemBody)

        fun bind(data: Hit, clickListener: OnItemClickListener)
        {
            itemView.setOnClickListener {
                clickListener.onItemClicked(data)
            }
        }

    }

    private class LoadingViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(@NonNull viewHolder: RecyclerView.ViewHolder, position: Int) {
        if (viewHolder is ItemViewHolder) {
            populateItemRows(viewHolder, position)
        } else if (viewHolder is LoadingViewHolder) {
            showLoadingView(viewHolder, position)
        }


    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    private fun showLoadingView(viewHolder: LoadingViewHolder, position: Int) {}

    @RequiresApi(Build.VERSION_CODES.O)
    private fun populateItemRows(viewHolder: ItemViewHolder, position: Int) {
        val item = itemList[position]
        if (item.story_title != null){
            viewHolder.titleItem.text = item.story_title
        } else{
            viewHolder.titleItem.text = item.title.toString()
        }

        viewHolder.bodyItem.text = item.author +" - "+ item.created_at

        viewHolder.bind(itemList[position], itemClickListener)
    }

    interface OnItemClickListener{
        fun onItemClicked(data: Hit)
    }

    fun removeAt(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }
}


