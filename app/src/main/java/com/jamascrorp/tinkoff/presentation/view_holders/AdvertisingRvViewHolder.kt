package com.jamascrorp.tinkoff.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.AdvertisingModel
import com.jamascrorp.tinkoff.databinding.AdvertisingCardBinding

class AdvertisingRvViewHolder(val binding: AdvertisingCardBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: AdvertisingModel) {
        binding.apply {
            imageAd.setImageResource(item.image)
            textAd.text = item.text
        }
    }
}