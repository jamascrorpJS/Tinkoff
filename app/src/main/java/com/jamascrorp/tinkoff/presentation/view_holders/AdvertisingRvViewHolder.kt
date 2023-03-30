package com.jamascrorp.tinkoff.presentation.view_holders

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jamascrorp.tinkoff.databinding.AdvertisingCardBinding
import com.jamascrorp.tinkoff.domain.entity.StoriesModel

class AdvertisingRvViewHolder(val binding: AdvertisingCardBinding): RecyclerView.ViewHolder(binding.root) {

    fun bind(item: StoriesModel) {
        binding.apply {
            Glide.with(itemView.context)
                .load(item.image)
                .into(binding.imageAd)
            textAd.text = item.name
        }
    }
}