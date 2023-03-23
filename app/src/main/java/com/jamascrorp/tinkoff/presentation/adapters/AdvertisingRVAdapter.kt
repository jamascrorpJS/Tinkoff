package com.jamascrorp.tinkoff.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.AdvertisingModel
import com.jamascrorp.tinkoff.databinding.AdvertisingCardBinding
import com.jamascrorp.tinkoff.presentation.view_holders.AdvertisingRvViewHolder

class AdvertisingRVAdapter(val list: List<AdvertisingModel>): RecyclerView.Adapter<AdvertisingRvViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdvertisingRvViewHolder {
        val layoutInflater = AdvertisingCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AdvertisingRvViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: AdvertisingRvViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = list.size
}