package com.example.comeprofit.ui.viewmodel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.comeprofit.data.model.CartItem
import com.example.comeprofit.data.model.Transaction
import com.example.comeprofit.data.repository.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {

    val transactionHistory: StateFlow<List<Transaction>> =
        repository.transactions.stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    @RequiresApi(Build.VERSION_CODES.O)
    fun createTransaction(items: List<CartItem>, totalPrice: Int) {
        val now = LocalDateTime.now()
        val formatter = java.time.format.DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")
        val formattedDate = now.format(formatter)
        val transactionId = "trx-$formattedDate"

        val transaction = Transaction(
            id = transactionId,
            items = items,
            totalPrice = totalPrice,
            dateTime = now
        )

        viewModelScope.launch {
            repository.addTransaction(transaction)
        }
    }
}

