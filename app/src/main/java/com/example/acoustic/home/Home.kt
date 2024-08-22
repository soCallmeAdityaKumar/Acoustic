package com.example.acoustic.home


import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel

import androidx.navigation.NavHostController
import com.example.acoustic.common.TYPE
import com.example.acoustic.data.dto.severalArtists.Artists
import com.example.acoustic.data.dto.categories.Categories
import com.example.acoustic.data.dto.new_releases.Releases
import com.example.acoustic.home.components.ArtistItem
import com.example.acoustic.home.viewModels.HomeViewModel
import com.example.acoustic.ui.theme.Acoustic
import com.example.acoustic.ui.theme.NavigationRowText
import com.example.acoustic.ui.theme.loginButtonColor

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Home(navController: NavHostController) {
    val scrollState= rememberScrollState()
    val viewModel:HomeViewModel= hiltViewModel()


    val severalArtist=viewModel.artistState.value.result
    val severalCategories=viewModel.categoriesState.value.result
    val newAlbums=viewModel.newAlbums.value.result


    Log.d("Home","Artists->${severalArtist?.artists.toString()}")
    Log.d("Home","Categories->${severalCategories?.categories?.items.toString()}")
    Log.d("Home","New Albums->${newAlbums?.albums?.items.toString()}")


    Column(modifier = Modifier
        .verticalScroll(scrollState)
        .padding(top = 90.dp)) {
        Row(modifier = Modifier.padding(10.dp)) {
           Button(onClick = { /*TODO*/ },
               shape = RoundedCornerShape(25.dp),
               colors = ButtonDefaults.buttonColors(backgroundColor = loginButtonColor, contentColor=Color.White)) {
               Text(text = "Music",fontSize = Acoustic.titleMedium.fontSize, style = NavigationRowText.bodyMedium)
           }
        }
        if(severalCategories!=null){
            TypeBox(title = "Artists", list =getSeveralArtistsItem(severalArtist),navController)
        }
        if(severalCategories!=null){
            TypeBox(title = "Categories", list = getSeveralCategoriesItem(severalCategories),navController)
        }
        if(newAlbums!=null){
            TypeBox(title = "New Releases", list = getNewReleasesItems(newAlbums),navController)
        }
        if(newAlbums!=null){
            TypeBox(title = "New Releases", list = getNewReleasesItems(newAlbums),navController)
        }
        if(newAlbums!=null){
            TypeBox(title = "New Releases", list = getNewReleasesItems(newAlbums),navController)
        }
    }
}

@Composable
fun TypeBox(
    title: String,
    list: List<HomeItem>,
    navController: NavHostController
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .padding()){
        Column(modifier = Modifier.fillMaxSize()) {
            Text(text = title, modifier = Modifier.padding(start = 20.dp, bottom = 10.dp), fontSize = Acoustic.titleLarge.fontSize, style = NavigationRowText.bodyMedium)
            LazyRow(modifier = Modifier.fillMaxWidth()){
                items(list.size) {
                    ArtistItem(name = list[it].name, url = list[it].url,list[it].id,navController,list[it].type)
                }
            }
        }
    }
}

fun getSeveralArtistsItem(list:Artists?):List<HomeItem>{
    val resultItem= mutableListOf<HomeItem>()
    if(list?.artists!=null){
        for(item in list.artists){
            resultItem.add(HomeItem(item.name,item.images[0].url,item.id,item.type))
        }
    }
    return resultItem

}
fun getSeveralCategoriesItem(list:Categories?):List<HomeItem>{
    val resultItem= mutableListOf<HomeItem>()
    if(list?.categories?.items!=null){
        for(item in list.categories.items){
            resultItem.add(HomeItem(item.name,item.icons[0].url,item.id, TYPE.CATEGORIES.toString()))
        }
    }
    return resultItem

}
fun getNewReleasesItems(list:Releases?):List<HomeItem>{
    val resultItem= mutableListOf<HomeItem>()
    if(list?.albums?.items!=null){
        for(item in list.albums.items){
            resultItem.add(HomeItem(item.name,item.images[0].url,item.id,item.type))
        }
    }
    return resultItem

}

data class HomeItem(val name:String,val url:String,val id:String,val type:String)

