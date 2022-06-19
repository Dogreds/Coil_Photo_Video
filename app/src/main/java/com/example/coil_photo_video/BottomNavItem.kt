package com.example.coil_photo_video

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    var name:String,
    var route:String,
    var icon:ImageVector,
    var badgeCount:Int = 0
)
