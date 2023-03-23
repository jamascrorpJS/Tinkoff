package com.jamascrorp.tinkoff.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OperationsModel(
    val icon: Int,
    val costName: String,

    val costType: String,
    val price: String,

    val priceType: String,
    val time: String,
    val color: String,
    val address: String
) : Parcelable