package com.example.comeprofit.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.*
import com.example.comeprofit.R
import com.example.comeprofit.ui.screen.CartScreen
import com.example.comeprofit.ui.screen.MenuScreen
import com.example.comeprofit.ui.screen.SplashScreen
import com.example.comeprofit.ui.screen.TransactionHistoryScreen
import com.example.comeprofit.ui.viewmodel.MenuViewModel

sealed class Screen(val route: String, val resourceId: Int, val icon: Int) {
    object Menu : Screen("menu", R.string.menu, R.drawable.ic_menu)
    object Cart : Screen("cart", R.string.cart, R.drawable.ic_cart)
    object History : Screen("history", R.string.history, R.drawable.ic_history)
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation() {
    var showSplash by remember { mutableStateOf(true) }

    if (showSplash) {
        SplashScreen(
            onSplashFinished = {
                showSplash = false
            }
        )
    } else {
        MainNavigation()
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
private fun MainNavigation() {
    val navController = rememberNavController()
    val viewModel: MenuViewModel = hiltViewModel()
    val items = listOf(Screen.Menu, Screen.Cart, Screen.History)

    Scaffold(
        bottomBar = {
            NavigationBar {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                val currentDestination = navBackStackEntry?.destination

                items.forEach { screen ->
                    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true
                    NavigationBarItem(
                        icon = {
                            Icon(
                                painter = painterResource(id = screen.icon),
                                contentDescription = null
                            )
                        },
                        label = { Text(stringResource(screen.resourceId)) },
                        selected = isSelected,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        colors = NavigationBarItemDefaults.colors(
                            selectedIconColor = Color(0xFF6B3E26),
                            unselectedIconColor = Color.Gray,
                            selectedTextColor = Color(0xFF6B3E26),
                            unselectedTextColor = Color.Gray,
                            indicatorColor = Color(0xFFFFE5D0)
                        )
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Menu.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(
                route = Screen.Menu.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )
                }
            ) {
                MenuScreen(navController = navController, viewModel = viewModel)
            }

            composable(
                route = Screen.Cart.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                }
            ) {
                CartScreen(navController = navController, viewModel = viewModel)
            }

            composable(
                route = Screen.History.route,
                enterTransition = {
                    slideIntoContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Left,
                        animationSpec = tween(300)
                    )
                },
                exitTransition = {
                    slideOutOfContainer(
                        towards = AnimatedContentTransitionScope.SlideDirection.Right,
                        animationSpec = tween(300)
                    )
                }
            ) {
                TransactionHistoryScreen()
            }
        }
    }
}
