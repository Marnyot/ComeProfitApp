package com.example.comeprofit.data.model

fun MenuItem.toCartItem(quantity: Int): CartItem {
    return CartItem(
        menuItem = this,
        quantity = quantity
    )
}

fun CartItem.toMenuItem(): MenuItem {
    return this.menuItem
}
