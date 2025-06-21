package com.example.comeprofit.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import java.time.LocalDateTime

@Entity(tableName = "transactions")
data class Transaction(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "items") val items: List<CartItem>,
    val totalPrice: Int,
    val dateTime: LocalDateTime
)
