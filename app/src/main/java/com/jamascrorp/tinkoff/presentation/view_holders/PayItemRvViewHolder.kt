package com.jamascrorp.tinkoff.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.databinding.PayVerticalCardBinding
import com.jamascrorp.tinkoff.domain.entity.PayItemModel

class PayItemRvViewHolder(val binding: PayVerticalCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PayItemModel) {

        binding.payIcon.setImageResource(item.image)
        binding.payName.text = item.name
    }
}