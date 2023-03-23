package com.jamascrorp.tinkoff.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.databinding.CostCardBinding

class OperationsRvViewHolder(val binding: CostCardBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: OperationsModel) {
        binding.costIcon.setImageResource(item.icon)
        binding.costName.text = item.costName
        binding.costType.text = item.costType
        binding.price.text = item.price
        binding.priceType.text = item.priceType
    }
}