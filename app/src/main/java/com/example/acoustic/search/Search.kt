package com.example.acoustic.search

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.acoustic.search.viewModels.SearchViewModels

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Search(navController: NavHostController,viewModel:SearchViewModels= hiltViewModel()) {

    val result=viewModel.searchResult.value.data
    Log.d("Search",result.let {
        it.toString()
    })
    var searchResults by remember { mutableStateOf(listOf<String>("albums")) }
    var value =""
    Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        if(result==null){
            Button(onClick = { viewModel.onSearch() }) {
                Text(text ="Search" )
            }
        }
        else{
            Text(text =result?.album.toString() )

        }
    }
}

