package com.example.coil_photo_video

sealed class Screen(var route:String) {
    object MainScreen:Screen(route = "main_screen")
    object DetailScreem:Screen(route = "detail_screen")

    fun withArgs(vararg args:String):String {
        return buildString {
            append(route)
            args.forEach { args->
                append("/$args")
            }
        }
    }
}
