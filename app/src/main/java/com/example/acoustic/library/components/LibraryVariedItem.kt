package com.example.acoustic.library.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.acoustic.data.dto.user_saved_playlists.Item
import com.example.acoustic.library.model.LibraryModel
import com.example.acoustic.library.model.toLibraryModel
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.loginButtonColor

@Composable
fun LibraryVariedItem(title:String,list:List<Item>,navController: NavHostController) {

    var playListDropDown by remember {
        mutableStateOf(false)
    }
    var showDropDown= true
    if(list.size==3)showDropDown=false
    val innerscrollState = rememberScrollState()

    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title, modifier = Modifier
                .wrapContentWidth()
                .padding(horizontal = 10.dp)
                .wrapContentHeight(), fontSize = 20.sp, color = loginButtonColor,
            style = NavigationRowText.bodyLarge
        )
        if(showDropDown) {
            IconButton(onClick = { playListDropDown = !playListDropDown }) {
                if (!playListDropDown) Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = "drop_down",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
                else Icon(
                    imageVector = Icons.Default.KeyboardArrowUp,
                    contentDescription = "drop_down",
                    modifier = Modifier.size(30.dp),
                    tint = Color.White
                )
            }
        }
    }
    Column(Modifier.fillMaxHeight().fillMaxWidth().verticalScroll(innerscrollState)) {
        if(!playListDropDown){
            list.take(3).forEach {
                LibraryItem(item = it.toLibraryModel(),navController)
            }
        }else{
            list.forEach {
                LibraryItem(item = it.toLibraryModel(),navController)
            }
        }
    }
}