package com.example.comeprofit.data.model

import androidx.annotation.DrawableRes
import java.io.Serializable

data class MenuItem(
    val id: String,
    val name: String,
    val category: String,
    val price: Int,
    @DrawableRes val image: Int
) : Serializable

