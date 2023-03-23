package com.jamascrorp.tinkoff.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.databinding.PayCardBinding
import com.jamascrorp.tinkoff.domain.entity.BookmarksModel

class BookmarksRvViewHolder(val binding: PayCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookmarksModel) {
        binding.payIcon.setImageResource(item.image)
        binding.payName.text = item.name
    }
}