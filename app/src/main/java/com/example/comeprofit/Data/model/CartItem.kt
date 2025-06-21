package com.example.comeprofit.data.model

import java.io.Serializable

data class CartItem(
    val menuItem: MenuItem,
    val quantity: Int
) : Serializable
