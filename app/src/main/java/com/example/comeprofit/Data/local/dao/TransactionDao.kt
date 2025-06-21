package com.example.comeprofit.data.local.dao

import androidx.room.*
import com.example.comeprofit.data.model.Transaction
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions ORDER BY dateTime DESC")
    fun getAll(): Flow<List<Transaction>>
}
