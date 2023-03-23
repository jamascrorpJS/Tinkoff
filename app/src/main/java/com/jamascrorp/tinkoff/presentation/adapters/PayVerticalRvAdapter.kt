package com.jamascrorp.tinkoff.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.databinding.PayVerticalCardBinding
import com.jamascrorp.tinkoff.presentation.view_holders.PayVerticalRvViewHolder

class PayVerticalRvAdapter(val list: List<PayModel>) :
    RecyclerView.Adapter<PayVerticalRvViewHolder>() {

    var clickOnPayItem: ((item: PayModel)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayVerticalRvViewHolder {
        val layoutInflater =
            PayVerticalCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PayVerticalRvViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PayVerticalRvViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.binding.paysCard.setOnClickListener {
            clickOnPayItem?.invoke(item)
        }
    }

    override fun getItemCount(): Int = list.size
}