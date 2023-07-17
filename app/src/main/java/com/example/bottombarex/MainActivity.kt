package com.example.bottombarex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bottombarex.screen.HomeScreen
import com.example.bottombarex.screen.MyPageScreen
import com.example.bottombarex.screen.SettingScreen
import com.example.bottombarex.ui.theme.BottomBarExTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BottomBarExTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Main()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Main() {
    val drawerNavController = rememberNavController()

    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)
    val selectedItem = remember { mutableStateOf(0) }

    val items = listOf(
        BottomNav.Home,
        BottomNav.MyPage,
        BottomNav.Setting
    )
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
            ) {
                Column(modifier = Modifier
                    .padding(start = 10.dp)
                    .fillMaxWidth()) {
                    Text(text = "_ 님")
                    Divider(Modifier.border(1.dp, Color.LightGray))
                    items.forEachIndexed { index, item ->
                        Row(
                            modifier = Modifier
                                .clickable {
                                    scope.launch { drawerState.close() }
                                    drawerNavController.navigate(item.screenRoute)
                                }
                                .padding(vertical = 12.dp)
                                .fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Image(
                                imageVector = ImageVector.vectorResource(item.icon),
                                contentDescription = stringResource(id = item.title))
                            Text(text = stringResource(id = item.title))
                        }
/*                        NavigationDrawerItem(
                            modifier = Modifier.fillMaxWidth(),
                            icon = { Icon(imageVector = ImageVector.vectorResource(id = item.icon), contentDescription = null, tint = Color.LightGray) },
                            label = { Text(text = stringResource(id = item.title)) },
                            selected = true,
                            onClick = {
                                scope.launch { drawerState.close() }
                                selectedItem.value = item.title
                                drawerNavController.navigate(item.screenRoute)
                            })*/
                    }
                }

            }
        },
    content = {
        Column(modifier = Modifier
            .padding(start = 10.dp, top = 10.dp)
            .fillMaxSize()) {
            Image(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_menu),
                contentDescription = null,
                modifier = Modifier.clickable { scope.launch { drawerState.open() } }
            )
            NavHost(drawerNavController, startDestination = BottomNav.Home.screenRoute) {
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
    })
}