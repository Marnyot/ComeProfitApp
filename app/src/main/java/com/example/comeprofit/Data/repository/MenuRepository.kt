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
                name = "Nasi Goreng ",
                price = 60000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Fried Rice"
            ),
            MenuItem(
                id = "2",
                name = "Nasi Goreng Babat",
                price = 55000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Fried Rice"
            ),
            MenuItem(
                id = "3",
                name = "Nasi Goreng Lap Cheong Halal",
                price = 58000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Fried Rice"
            ),
            MenuItem(
                id = "4",
                name = "Nasi Goreng Buntut",
                price = 150000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Fried Rice"
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
