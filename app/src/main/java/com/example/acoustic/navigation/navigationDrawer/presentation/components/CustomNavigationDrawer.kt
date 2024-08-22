package com.example.acoustic.navigation.navigationDrawer.presentation.components

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.acoustic.login.domain.data.SharedPref
import com.example.acoustic.navigation.navigationDrawer.domain.model.NavigationItem
import com.example.acoustic.navigation.navigationDrawer.domain.model.navigationDrawerItem
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.appCustomBackground
import com.example.acoustic.ui.theme.loginButtonColor

@Composable
fun CustomNavigationDrawer(
    selectedNavigationItem: NavigationItem,
    onNavigationItemClick: (NavigationItem) -> Unit,
    onCloseClick: () -> Unit,
    isLoggout:()->Unit
) {

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(300.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(appCustomBackground),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()){
            Box(modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.Black)
            ) {
                IconButton(onClick = onCloseClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Arrow Icon",
                        tint = Color.White
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(14.dp))
        navigationDrawerItem().take(3).forEach { navigationItem->
            NavigationDrawerItemView(
                navigationItem = navigationItem,
                selected = navigationItem==selectedNavigationItem,
                onClick ={onNavigationItemClick(navigationItem)}
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
        Spacer(modifier = Modifier.height(304.dp))
        navigationDrawerItem().takeLast(1).forEach { navigationItem ->
            Row(modifier = Modifier
                .padding(horizontal = 10.dp)
                .clip(RoundedCornerShape(10.dp))
                .clickable {
//                    SharedPref(activity.baseContext).delete("USER_TOKEN")
                    isLoggout()
                },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ){
                Text(text=navigationItem.title,
                    Modifier.padding(10.dp).background(loginButtonColor),
                    color = Color.White,
                    style = NavigationRowText.bodyLarge
                )
            }
        }

    }
}