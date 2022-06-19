package com.example.coil_photo_video

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun Navigate(navController: NavHostController) {
    NavHost(navController = navController, startDestination =  "home" )  {
        composable("home") {
                HomeScreen()
        }
        composable("chat") {
            ChatScreen()
        }
        composable("settings") {
            Settinscreen()
        }
    }



}

@ExperimentalMaterialApi
@Composable
fun BottomNavigatonBar(
    items:List<BottomNavItem>,
    navController: NavHostController,
    modifier: Modifier = Modifier,
    onItemClick:(BottomNavItem) -> Unit
) {
    var backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(modifier = modifier, backgroundColor = Color.DarkGray,elevation = 5.dp) {
        items.forEach { item ->
            var selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(selected = selected, onClick = { onItemClick(item) },
              selectedContentColor =  Color.Green  ,
                unselectedContentColor = Color.Blue,
        icon =  {
            Column(horizontalAlignment = CenterHorizontally) {
                if(item.badgeCount > 0 ) {
                    BadgeBox(badgeContent = {Text(text = item.badgeCount.toString())}) {
                        Icon(imageVector = item.icon, contentDescription = item.name)
                    }

                }
                else {
                    Icon(imageVector = item.icon, contentDescription = item.name )
                }
                if(selected) {
                    Text(text = item.name,
                    textAlign = TextAlign.Center,
                    fontSize = 10.sp)
                }
                
            }

        })
        }
        
    }
}


@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
        Text(text = "Home Screen")
    }
}

@Composable
fun ChatScreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = "Chat_Screen")
    }
}

@Composable
fun Settinscreen() {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Text(text = "Settings_Screen")
    }
}