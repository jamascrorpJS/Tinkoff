package com.jamascrorp.tinkoff.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayItemModel(
    val address: String,
    val color: String,
    val image: Int,
    val name: String,
) : Parcelable