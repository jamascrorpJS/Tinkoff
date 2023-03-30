package com.jamascrorp.tinkoff.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.jamascrorp.tinkoff.domain.entity.OperationsModel

class OperationsDiffUtil: DiffUtil.ItemCallback<OperationsModel>() {

    override fun areItemsTheSame(oldItem: OperationsModel, newItem: OperationsModel): Boolean {

        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: OperationsModel, newItem: OperationsModel): Boolean {
        return oldItem == newItem
    }
}