package com.jamascrorp.tinkoff.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.databinding.PayVerticalCardBinding
import com.jamascrorp.tinkoff.domain.entity.PayItemModel
import com.jamascrorp.tinkoff.presentation.view_holders.PayItemRvViewHolder

class PayItemRvAdapter(private val list: List<PayItemModel>) :
    RecyclerView.Adapter<PayItemRvViewHolder>() {
    var clickOnPayItem: ((item: PayItemModel)->Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PayItemRvViewHolder {
        val layoutInflater =
            PayVerticalCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PayItemRvViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: PayItemRvViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.binding.paysCard.setOnClickListener {
            clickOnPayItem?.invoke(item)
        }
    }

    override fun getItemCount(): Int = list.size
}