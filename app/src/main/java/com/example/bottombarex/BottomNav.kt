package com.example.bottombarex

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.bottombarex.screen.HomeScreen
import com.example.bottombarex.screen.MyPageScreen
import com.example.bottombarex.screen.SettingScreen

sealed class BottomNav(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Home: BottomNav(R.string.home, R.drawable.ic_home, HOME)
    object MyPage: BottomNav(R.string.my_page, R.drawable.ic_my_page, MY_PAGE)
    object Setting: BottomNav(R.string.setting, R.drawable.ic_setting, SETTING)
}

const val HOME = "Home"
const val MY_PAGE = "MyPage"
const val SETTING = "Setting"



@Composable
fun NavigationGraph(navController: NavHostController) {
    NavHost(navController, startDestination = BottomNav.Home.screenRoute) {
        composable(BottomNav.Home.screenRoute) {
            HomeScreen()
        }
        composable(BottomNav.MyPage.screenRoute) {
            MyPageScreen()
        }
        composable(BottomNav.Setting.screenRoute) {
            SettingScreen()
        }
    }
}