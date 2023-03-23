package com.jamascrorp.tinkoff.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.databinding.PayVerticalCardBinding

class PayVerticalRvViewHolder(val binding: PayVerticalCardBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: PayModel) {
        binding.payIcon.setImageResource(item.image)
        binding.payName.text = item.text
    }
}