package com.jamascrorp.tinkoff.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jamascrorp.tinkoff.domain.entity.OperationsModel
import com.jamascrorp.tinkoff.databinding.CostCardBinding
import com.jamascrorp.tinkoff.presentation.view_holders.OperationsRvViewHolder

class OperationsRvAdapter(val list: List<OperationsModel>): RecyclerView.Adapter<OperationsRvViewHolder>() {

    var clickOnTransactionsItem: ((item: OperationsModel)->Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OperationsRvViewHolder {
        val layoutInflater = CostCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OperationsRvViewHolder(layoutInflater)
    }

    override fun onBindViewHolder(holder: OperationsRvViewHolder, position: Int) {
        val item = list[position]
        holder.bind(item)
        holder.binding.transactCard.setOnClickListener {
            clickOnTransactionsItem?.invoke(item)
        }
    }

    override fun getItemCount(): Int = list.size
}