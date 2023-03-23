package com.jamascrorp.tinkoff.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.databinding.PayCardBinding
import com.jamascrorp.tinkoff.domain.entity.BookmarksModel
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.presentation.view_holders.BookmarksRvViewHolder

class BookmarksRvAdapter(val list: List<BookmarksModel>) : RecyclerView.Adapter<BookmarksRvViewHolder>() {

    var clickOnPayItem: ((item: BookmarksModel) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarksRvViewHolder {
        val layoutInflater =
            PayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookmarksRvViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: BookmarksRvViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.binding.payCard.setOnClickListener {
            clickOnPayItem?.invoke(item)
        }
    }

    override fun getItemCount(): Int = list.size
}