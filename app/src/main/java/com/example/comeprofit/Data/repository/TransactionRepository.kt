package com.example.comeprofit.data.repository

import com.example.comeprofit.data.local.dao.TransactionDao
import com.example.comeprofit.data.model.Transaction
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionRepository @Inject constructor(
    private val dao: TransactionDao
) {
    val transactions: Flow<List<Transaction>> = dao.getAll()

    suspend fun addTransaction(transaction: Transaction) {
        dao.insert(transaction)
    }
}
