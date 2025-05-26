package com.example.comeprofit.data.repository

import com.example.comeprofit.data.model.MenuItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton
import com.example.comeprofit.R


@Singleton
class MenuRepository @Inject constructor() {
    suspend fun getMenuItems(): List<MenuItem> = withContext(Dispatchers.IO) {
        getDummyMenuItems()
    }

    private fun getDummyMenuItems(): List<MenuItem> {
        return listOf(
            MenuItem(
                id = "1",
                name = "Nasi Goreng Spesial",
                price = 35000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Nasi"
            ),
            MenuItem(
                id = "2",
                name = "Nasi Ayam Penyet",
                price = 32000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Nasi"
            ),
            MenuItem(
                id = "3",
                name = "Nasi Uduk",
                price = 25000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Nasi"
            ),
            MenuItem(
                id = "4",
                name = "Mie Goreng",
                price = 28000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Mie"
            ),
            MenuItem(
                id = "5",
                name = "Mie Ayam",
                price = 30000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Mie"
            ),
            MenuItem(
                id = "6",
                name = "Kwetiau Goreng",
                price = 32000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Mie"
            ),
            MenuItem(
                id = "7",
                name = "Ayam Bakar",
                price = 40000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Ayam"
            ),
            MenuItem(
                id = "8",
                name = "Ayam Goreng",
                price = 38000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Ayam"
            )
        )
    }
}
