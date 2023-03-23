package com.jamascrorp.tinkoff.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.PayModel
import com.jamascrorp.tinkoff.databinding.PayCardBinding
import com.jamascrorp.tinkoff.presentation.view_holders.PayRvViewHolder

class PayRvAdapter(val list: List<PayModel>) : RecyclerView.Adapter<PayRvViewHolder>() {

    var clickOnPayItem: ((item: PayModel)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayRvViewHolder {
        val layoutInflater =
            PayCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PayRvViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PayRvViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.binding.payCard.setOnClickListener {
            clickOnPayItem?.invoke(item)
        }
    }

    override fun getItemCount(): Int = list.size
}