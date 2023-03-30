package com.jamascrorp.tinkoff.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class StoriesModel(
    val image: String = "",
    val name: String = ""

): Parcelable