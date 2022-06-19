package com.example.coil_photo_video

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.animation.OvershootInterpolator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.ImagePainter
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import com.example.coil_photo_video.ui.theme.Coil_Photo_VideoTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @ExperimentalFoundationApi
    @ExperimentalMaterialApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var scafoldstate = rememberScaffoldState()
            var scope = rememberCoroutineScope()
            var navController  = rememberNavController()
            Surface(color = Color.Blue, modifier = Modifier.fillMaxSize()) {
                dropdown(text = "Hellow", modifier = Modifier.padding(15.dp)) {
                    Text(text = "This is now revaled",
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .background(Color.Yellow))
                    
                }

            }
          //  lazycolum(this)
          //  com.example.coil_photo_video.Navigation()

         //   Scaffold(bottomBar = {
           //     BottomNavigatonBar(items = listOf(BottomNavItem(
             //       name =  "Home",
              //      route = "home",
               //     icon = Icons.Default.Home,

              //  ),
                //    BottomNavItem(
                  //      name =  "Chat",
                    //    route = "chat",
                      //  icon = Icons.Default.Notifications,

                      //  ),
                 //   BottomNavItem(
                   //     name =  "Settings",
                     //   route = "settings",
                       // icon = Icons.Default.Settings,

                      //  )),
                  //  navController = navController,
                  //  onItemClick = {
                    //    navController.navigate(it.route)
                   // })
         //   }) {
           //         Navigate(navController = navController)
            //}
         //   Scaffold(


           //     drawerContent =  {
             //       navcontorler()
               //     drawbody(items = listOf(
                 //       NavDrawerItem("home", title =  "Home",contentDesription = "Go to home screen",
                   //     Icons.Default.Home),
                     //   NavDrawerItem("settings", title =  "Setting",contentDesription = "Go to settings screen",
                       //     Icons.Default.Settings),
                      //  NavDrawerItem("help", title =  "Home",contentDesription = "Get Help",
                       //     Icons.Default.ExitToApp)
                   // ), onitemClick = {
                     //   Toast.makeText(applicationContext, "Ты нажал на кнопку ${it.title}", Toast.LENGTH_SHORT).show()
                   // } )
               // },scaffoldState = scafoldstate
               // , topBar = { appbar (onnavigationclicklambda = {
                 //   scope.launch {
                   //     scafoldstate.drawerState.open()
                   // }


               // })

              //  }
           // ) {

        //    }

            }
        }
    }

@Composable
fun dropdown(text:String,
             modifier: androidx.compose.ui.Modifier = androidx.compose.ui.Modifier,
             initiallyOpened:Boolean = false, content:@Composable () -> Unit) {
    var open by remember {
        mutableStateOf(initiallyOpened)
    }
    var alpha = animateFloatAsState(targetValue = if(open) 1f else 0f,
    animationSpec = tween(durationMillis = 300))

    var rotatex = animateFloatAsState(targetValue = if(open) 0f else -90f,
        animationSpec = tween(durationMillis = 300))
    
    Column(modifier = modifier.fillMaxWidth()) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically) {
            Text(text = text, color = Color.White, fontSize = 16.sp)
            Icon( imageVector = Icons.Default.ArrowDropDown , contentDescription = "Open",
            tint = Color.White, modifier = Modifier
                    .clickable {
                        open = !open
                    }
                    .scale(1f, if (open) -1f else 1f))
        }
        Spacer(modifier = Modifier.height(10.dp))
        Box(contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer {
                transformOrigin = TransformOrigin(0.5f, 0f)
                rotationX = rotatex.value
            }
            .alpha(alpha.value)) {
            content()
        }
        
    }


}



@Composable
fun navcontorler() {
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 64.dp),
    contentAlignment = Alignment.Center) {
        Text(text = "header", fontSize = 60.sp)
    }
}

@Composable
fun drawbody(items:List<NavDrawerItem>,
modifier: Modifier = Modifier,
itemtextstyle:TextStyle = TextStyle(fontSize = 18.sp),
onitemClick:(NavDrawerItem) -> Unit) {
    LazyColumn(modifier) {
        items(items) {item-> 
            Row(modifier
                .fillMaxWidth()
                .clickable {
                    onitemClick(item)
                }
                .padding(16.dp)) {
                Icon(imageVector = item.icon, contentDescription = item.contentDesription) 
                Spacer(modifier = Modifier.width(16.dp))
                Text(text = item.title,
                    style= itemtextstyle,
                modifier = Modifier.weight(1f))

            }
        }
    }

}

@Composable
fun appbar(onnavigationclicklambda:() -> Unit) {
    TopAppBar(title = {
        Text(text = "My cool app")
    }, backgroundColor = Color.Red,
    navigationIcon = { IconButton(onClick = {onnavigationclicklambda}) {
        Icon(imageVector = Icons.Default.Menu, contentDescription = "Togle Drawer" )

    }})



}


@Composable
fun splashscreen() {
var remeber = rememberNavController()
 NavHost(navController = remeber, startDestination = "splash_batman" ) {
     composable("splash_batman"){
            created(navController = remeber)
     }
     composable("main_screen") {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
           Image(painter = painterResource(id = R.drawable.potter), contentDescription = "Potter" )
        }
     }
 }
}

@SuppressLint("RememberReturnType")
@Composable
fun created(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }
        LaunchedEffect(key1 = true) {
            scale.animateTo(
                targetValue = 0.7f,
                animationSpec = tween(
                    durationMillis = 2000,
                    easing = {
                        OvershootInterpolator(2f).getInterpolation(it)
                    }
                )
            )
            delay(5000L)
             navController.navigate("main_screen")
        }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Image(painter = painterResource(id = R.drawable.splash_batman), contentDescription = "BATMAN" )
    }
}



@Composable
fun listed(context: Context) {
    LazyRow {
       items((1..100).toList()) {

               Card(modifier = Modifier
                   .width(300.dp)
                   .height(300.dp)
                   .padding(8.dp)
                   .clickable {
                       Toast
                           .makeText(context, "ты нажа на кнопку ${it}", Toast.LENGTH_SHORT)
                           .show()
                   }) {
                   Column(
                       modifier = Modifier.fillMaxSize(),
                       verticalArrangement = Arrangement.Center,
                       horizontalAlignment = Alignment.CenterHorizontally
                   ) {
                       Text(text = "Number is ${it}", fontSize = 12.sp, color = Color.Red)
                   }


               }

           }

    }
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun edittext() {
    var textvalue = remember {mutableStateOf("")}

    var colorPrimari = colorResource(id = R.color.design_default_color_primary)
   OutlinedTextField (

       value = textvalue.value, onValueChange = {
        textvalue.value = it
    }, label = { Text(text = "Phone")},
    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password))
}


@Composable
fun alertdialog() {
    var created = remember {
        mutableStateOf(true)
    }
    if(created.value) {
        AlertDialog(onDismissRequest = { created.value = false },
        title =  { Text(text = "Хочешь доавить новую заметку", fontSize = 19.sp)},
        text =  { Text(text = "This is jetpcak ")},
        confirmButton = { Button(onClick = { created.value = false }) {
            Text(text = "close", color = Color.Red)

        }})

            

    }
}



@SuppressLint("UnrememberedMutableState")
@Composable
fun animated(context: Context) {
    var sizestate  by remember { mutableStateOf(200.dp)}
    val size by animateDpAsState(targetValue =
    sizestate,
    spring(Spring.DampingRatioMediumBouncy))

    var infiniteTransition = rememberInfiniteTransition()
    val color by infiniteTransition.animateColor(initialValue = Color.Blue,
        targetValue = Color.Gray ,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 2000),repeatMode = RepeatMode.Reverse
        ))
    Box(modifier = Modifier
        .size(size)
        .background(color), contentAlignment = Alignment.Center) {
        Button(onClick = { sizestate += 50.dp }) {
            Text(text = "Scale", fontSize = 24.sp)
            Toast.makeText(context, "Ты увеличел разрешение на ${sizestate} dp", Toast.LENGTH_SHORT).show()

        }

    }


}



@ExperimentalMaterialApi
@Composable
fun bottomfragment() {
    var stated = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed,
        animationSpec = spring(dampingRatio = Spring.DampingRatioLowBouncy))
    var scallfoldstate = rememberBottomSheetScaffoldState(bottomSheetState = stated)
    var remember = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = scallfoldstate,

        sheetContent = {
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
                contentAlignment = Alignment.Center) {
                var image = rememberImagePainter(data = "https://i.ibb.co/nsYd9s9/batmen.jpg", builder =  {
                    placeholder(R.drawable.potter)
                    crossfade(5000)
                    transformations(GrayscaleTransformation(),
                        CircleCropTransformation())
                } )
                var taked = image.state
                Image(painter = image, contentDescription = "batman" )
            }


        }, sheetBackgroundColor = Color.White) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center) {
            Button(onClick = {
                remember.launch {
                    if (stated.isCollapsed) {
                        stated.expand()
                    } else {
                        stated.collapse()
                    }

                }
            }) {
                Text(text = "Toggle sheet ")

            }

        }

    }
}



    




@Composable
fun columimgae() {
    Box(modifier = Modifier
        .height(400.dp)
        .width(400.dp),contentAlignment = Alignment.Center) {
        var painter = rememberImagePainter(data = "https://i.ibb.co/nsYd9s9/batmen.jpg", builder =
        {
            placeholder(R.drawable.potter)
            crossfade(5000)
            transformations(GrayscaleTransformation(),
             CircleCropTransformation())
        })
        var painted = painter.state
        Image(painter = painter, contentDescription = "Logo Image" )

        if(painted is ImagePainter.State.Loading) {
            CircularProgressIndicator()
        }

    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Coil_Photo_VideoTheme {
        Greeting("Android")
    }
}