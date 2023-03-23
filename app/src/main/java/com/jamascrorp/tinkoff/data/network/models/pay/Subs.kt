package com.jamascrorp.tinkoff.data.network.models.pay

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subs(
    val address: String,
    val color: String,
    val icon: String,
    val name: String
): Parcelable