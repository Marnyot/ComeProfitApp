package com.example.comeprofit.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comeprofit.data.model.CartItem
import com.example.comeprofit.data.model.MenuItem
import com.example.comeprofit.data.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
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

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    private val _totalCartPrice = MutableStateFlow(0)
    val totalCartPrice: StateFlow<Int> = _totalCartPrice.asStateFlow()

    private val _cartItemCount = MutableStateFlow(0)
    val cartItemCount: StateFlow<Int> = _cartItemCount.asStateFlow()

    val filteredMenuItems: StateFlow<List<MenuItem>> = combine(
        _menuItems,
        _selectedCategory,
        _searchQuery
    ) { items, category, query ->
        items.filter { item ->
            (category == null || category == "Semua" || item.category == category) &&
                    (query.isBlank() || item.name.contains(query, ignoreCase = true))
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

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
            val existingItemIndex = currentCartItems.indexOfFirst { it.menuItem.id == menuItem.id }

            if (existingItemIndex != -1) {
                val existingItem = currentCartItems[existingItemIndex]
                currentCartItems[existingItemIndex] = existingItem.copy(quantity = existingItem.quantity + 1)
            } else {
                currentCartItems.add(CartItem(menuItem = menuItem, quantity = 1))
            }

            _cartItems.value = currentCartItems
            updateCartTotals()
        }
    }

    fun removeFromCart(cartItem: CartItem) {
        viewModelScope.launch {
            val currentCartItems = _cartItems.value.toMutableList()
            val itemIndex = currentCartItems.indexOfFirst { it.menuItem.id == cartItem.menuItem.id }

            if (itemIndex != -1) {
                val existingItem = currentCartItems[itemIndex]
                if (existingItem.quantity > 1) {
                    currentCartItems[itemIndex] = existingItem.copy(quantity = existingItem.quantity - 1)
                } else {
                    currentCartItems.removeAt(itemIndex)
                }

                _cartItems.value = currentCartItems
                updateCartTotals()
            }
        }
    }

    fun removeItemCompletely(menuItemId: String) {
        viewModelScope.launch {
            val currentCartItems = _cartItems.value.toMutableList()
            val itemIndex = currentCartItems.indexOfFirst { it.menuItem.id == menuItemId }

            if (itemIndex != -1) {
                currentCartItems.removeAt(itemIndex)
                _cartItems.value = currentCartItems
                updateCartTotals()
            }
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
        return filteredMenuItems.value
    }

    private fun updateCartTotals() {
        val items = _cartItems.value
        _totalCartPrice.value = items.sumOf { it.menuItem.price * it.quantity }
        _cartItemCount.value = items.sumOf { it.quantity }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun checkout(transactionViewModel: TransactionViewModel) {
        val itemsToBuy = _cartItems.value
        val total = _totalCartPrice.value

        transactionViewModel.createTransaction(itemsToBuy, total)
        clearCart()
    }
}
