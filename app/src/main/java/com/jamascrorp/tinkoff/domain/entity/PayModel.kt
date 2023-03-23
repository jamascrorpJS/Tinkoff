package com.jamascrorp.tinkoff.domain.entity

import android.os.Parcelable
import com.jamascrorp.tinkoff.data.network.models.pay.Subs
import kotlinx.parcelize.Parcelize

@Parcelize
data class PayModel(
    val image: Int,
    val text: String,
    val id: String,
    val subs: List<Subs>
): Parcelable