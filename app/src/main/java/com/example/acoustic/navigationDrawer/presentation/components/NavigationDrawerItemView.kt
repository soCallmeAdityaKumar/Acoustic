package com.example.acoustic.navigationDrawer.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.acoustic.navigationDrawer.domain.model.NavigationItem
import com.example.acoustic.ui.theme.Acoustic
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.cardWhite
import com.example.acoustic.ui.theme.cardblack
import com.example.acoustic.ui.theme.loginButtonColor

@Composable
fun NavigationDrawerItemView(
    navigationItem: NavigationItem,
    selected:Boolean,
    onClick:()->Unit
) {
    Spacer(modifier = Modifier.height(20.dp))
    Row(modifier = Modifier
        .padding(horizontal = 20.dp)
        .fillMaxWidth()
        .clip(RoundedCornerShape(10.dp))
        .clickable { onClick() }
        .background(color = if (selected) loginButtonColor else Color.Black),
        verticalAlignment = Alignment.CenterVertically
    ){
        Icon(
            modifier = Modifier
                .padding(10.dp)
                .size(15.dp),
            imageVector = if(selected)navigationItem.selectedIcon
            else navigationItem.unselectedIcon,
            contentDescription = "Navigation_icon",
            tint = if(selected) cardWhite else Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text=navigationItem.title,
            style = NavigationRowText.bodyLarge,
            color = if(selected) cardWhite else Color.White)
    }
}