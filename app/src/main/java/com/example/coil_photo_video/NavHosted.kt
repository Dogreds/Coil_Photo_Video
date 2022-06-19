package com.example.coil_photo_video

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    var navcontrole = rememberNavController()
    NavHost(navController = navcontrole, startDestination =  Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
                    MainScreen(navController = navcontrole)
        }
        composable(
            route = Screen.DetailScreem.route + "/{name}",
            arguments = listOf(navArgument("name", ) {
                type = NavType.StringType
                defaultValue = "Hellow world"
                nullable = true
            })
        ) {
            entry-> DetailScreem(name = entry.arguments?.getString("name") )
        }
    }
}


@Composable
fun MainScreen(navController: NavController) {
    var text by remember {
        mutableStateOf("")
    }
    Column(verticalArrangement = Arrangement.Center,
    modifier = Modifier
        .fillMaxWidth()
        .padding(horizontal = 50.dp)) {
        TextField(value = text, onValueChange =  {text = it},
        modifier = Modifier.fillMaxWidth())

    }
    Spacer(modifier = Modifier.height(8.dp))
    Button(onClick = { navController.navigate(Screen.DetailScreem.withArgs(text))}) {
        Text(text =  "To detail Screen")
        
    }

}

@Composable
fun DetailScreem(name:String?) {
    Box(modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center) {
        Text(text = "Hellow ${name}")
    }


}