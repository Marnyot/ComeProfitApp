package com.example.comeprofit.ui.screen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.comeprofit.data.model.Transaction
import com.example.comeprofit.ui.components.EmptyStateMessage
import com.example.comeprofit.ui.viewmodel.TransactionViewModel
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionHistoryScreen(
    viewModel: TransactionViewModel = hiltViewModel(),
) {
    val transactionHistory by viewModel.transactionHistory.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Riwayat Transaksi") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.onSurface
                )
            )
        }
    ) { innerPadding ->
        if (transactionHistory.isEmpty()) {
            EmptyTransactionMessage(modifier = Modifier.padding(innerPadding))
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(transactionHistory) { transaction ->
                    TransactionCard(transaction)
                }
            }
        }
    }
}

@Composable
fun EmptyTransactionMessage(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        EmptyStateMessage(
            title = "Riwayat Kosong",
            message = "Belum ada transaksi yang dilakukan."
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TransactionCard(transaction: Transaction) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("ID: ${transaction.id}", style = MaterialTheme.typography.labelMedium)
            Text(
                "Tanggal: ${transaction.dateTime.format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm"))}",
                style = MaterialTheme.typography.labelMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            transaction.items.forEach { item ->
                Text(text = "${item.menuItem.name} x${item.quantity} - Rp ${item.menuItem.price * item.quantity}")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text("Total: Rp ${transaction.totalPrice}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
