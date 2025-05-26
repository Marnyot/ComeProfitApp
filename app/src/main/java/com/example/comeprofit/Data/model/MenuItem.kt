package com.example.comeprofit.data.model

import androidx.annotation.DrawableRes

data class MenuItem(
    val id: String,
    val name: String,
    val price: Int,
    @DrawableRes val image: Int,
    val category: String,
)
