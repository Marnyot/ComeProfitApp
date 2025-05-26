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
                name = "Nasi Goreng Seafood",
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
                name = "Carbonara",
                price = 66000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Spaghetti"
            ),
            MenuItem(
                id = "6",
                name = "Gamberetti",
                price = 66000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Spaghetti"
            ),
            MenuItem(
                id = "7",
                name = "Salmon with Cherry Tomato",
                price = 66000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Spaghetti"
            ),
            MenuItem(
                id = "8",
                name = "Bolognese",
                price = 66000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Spaghetti"
            ),
            MenuItem(
                id = "9",
                name = "Fish & Chips",
                price = 60000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Kids Meal"
            ),
            MenuItem(
                id = "10",
                name = "Chicken Parmigiana",
                price = 65000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Kids Meal"
            ),
            MenuItem(
                id = "11",
                name = "Omelet",
                price = 45000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Kids Meal"
            ),
            MenuItem(
                id = "12",
                name = "Lidah Lombok Ijo",
                price = 58000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Main Course"
            ),
            MenuItem(
                id = "13",
                name = "Ayam Romansa",
                price = 85000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Main Course"
            ),
            MenuItem(
                id = "14",
                name = "Ayam Panggan Madu",
                price = 85000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Main Course"
            ),
            MenuItem(
                id = "15",
                name = "Soto Betawi",
                price = 68000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Main Course"
            ),
            MenuItem(
                id = "16",
                name = "Tenderloin Steak",
                price = 235000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Main Course"
            ),
            MenuItem(
                id = "17",
                name = "Sirloin Steak",
                price = 250000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Main Course"
            ),
            MenuItem(
                id = "18",
                name = "Crab Rangoon",
                price = 48000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            ),
            MenuItem(
                id = "19",
                name = "Chicken In A Basket",
                price = 58000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            ),
            MenuItem(
                id = "20",
                name = "Tahu Kriuk",
                price = 34000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            ),
            MenuItem(
                id = "21",
                name = "Tahu Kipas",
                price = 46000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            ),
            MenuItem(
                id = "22",
                name = "Paru Crispy",
                price = 48000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            ),
            MenuItem(
                id = "23",
                name = "Mini Wonton",
                price = 38000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            ),
            MenuItem(
                id = "24",
                name = "Tahu Mercon",
                price = 40000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            ),
            MenuItem(
                id = "25",
                name = "Banana Bites",
                price = 45000,
                image = R.drawable.nasi_goreng_spesial,
                category = "Light Bites"
            )
        )
    }
}
