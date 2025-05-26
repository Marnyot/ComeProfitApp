package com.example.comeprofit.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comeprofit.data.model.CartItem
import com.example.comeprofit.data.model.MenuItem
import com.example.comeprofit.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _menuItems = MutableStateFlow<List<MenuItem>>(emptyList())
    val menuItems: StateFlow<List<MenuItem>> = _menuItems.asStateFlow()

    private val _cartItems = MutableStateFlow<List<CartItem>>(emptyList())
    val cartItems: StateFlow<List<CartItem>> = _cartItems.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _selectedCategory = MutableStateFlow<String?>(null)
    val selectedCategory: StateFlow<String?> = _selectedCategory.asStateFlow()

    private val _totalCartPrice = MutableStateFlow(0)
    val totalCartPrice: StateFlow<Int> = _totalCartPrice.asStateFlow()

    private val _cartItemCount = MutableStateFlow(0)
    val cartItemCount: StateFlow<Int> = _cartItemCount.asStateFlow()

    init {
        loadMenuItems()
    }

    fun loadMenuItems() {
        viewModelScope.launch {
            _isLoading.value = true
            _menuItems.value = menuRepository.getMenuItems()
            _isLoading.value = false
        }
    }

    fun addToCart(menuItem: MenuItem) {
        viewModelScope.launch {
            val currentCartItems = _cartItems.value.toMutableList()
            val existingItem = currentCartItems.find { it.menuItem.id == menuItem.id }

            if (existingItem != null) {
                // Update quantity if item already exists in cart
                val index = currentCartItems.indexOf(existingItem)
                currentCartItems[index] = existingItem.copy(quantity = existingItem.quantity + 1)
            } else {
                // Add new item to cart
                currentCartItems.add(CartItem(menuItem, 1))
            }

            _cartItems.value = currentCartItems
            updateCartTotals()
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            val currentCartItems = _cartItems.value.toMutableList()

            if (cartItem.quantity > 1) {
                // Decrease quantity if more than 1
                val index = currentCartItems.indexOf(cartItem)
                currentCartItems[index] = cartItem.copy(quantity = cartItem.quantity - 1)
            } else {
                // Remove item from cart if quantity is 1
                currentCartItems.remove(cartItem)
            }

            _cartItems.value = currentCartItems
            updateCartTotals()
        }
    }

    fun removeItemCompletely(cartItem: CartItem) {
        viewModelScope.launch {
            val currentCartItems = _cartItems.value.toMutableList()
            currentCartItems.remove(cartItem)
            _cartItems.value = currentCartItems
        }
    }

    fun clearCart() {
        viewModelScope.launch {
            _cartItems.value = emptyList()
            updateCartTotals()
        }
    }

    fun setSelectedCategory(category: String?) {
        _selectedCategory.value = category
    }

    fun getFilteredMenuItems(): List<MenuItem> {
        val category = _selectedCategory.value
        return if (category == null || category == "Semua") {
            _menuItems.value
        } else {
            _menuItems.value.filter { it.category == category }
        }
    }

    private fun updateCartTotals() {
        val items = _cartItems.value
        _totalCartPrice.value = items.sumOf { it.menuItem.price * it.quantity }
        _cartItemCount.value = items.size
    }
}
