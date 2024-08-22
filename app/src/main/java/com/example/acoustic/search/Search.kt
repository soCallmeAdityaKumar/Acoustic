package com.example.acoustic.search

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.acoustic.search.components.AlbumCard
import com.example.acoustic.search.data.album.Item
import com.example.acoustic.search.viewModels.SearchViewModels
import com.example.acoustic.ui.theme.albumBlackBackground

@OptIn(ExperimentalMaterial3Api::class)
@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun Search(navController: NavHostController,viewModel: SearchViewModels = hiltViewModel()) {

    val result=viewModel.searchResult.value.data
    Log.d("Search",result.let {
        it.toString()
    })
    var text by remember {
        mutableStateOf("")
    }
    var active by remember{
        mutableStateOf(false)
    }
    val searchResult=viewModel.searchResult
    Column(modifier = Modifier
        .fillMaxSize(),
    )
    {
        Row (modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)){
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                , query =text ,
                onQueryChange ={
                    text=it
                    viewModel.onSearchQueryChange(text)
                } ,
                onSearch = {
                    active=false
                },
                active =active ,
                onActiveChange ={
                    active=false
                },
                placeholder = {
                    Text("Search")
                },
                leadingIcon = {
                    Icon(
                        modifier = Modifier.clickable {
                              viewModel.onSearch()
                        },
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search_Icon_Search"
                    )
                },
                trailingIcon = {
                    if(text.isNotEmpty()){
                        Icon(modifier = Modifier.clickable {
                            text=""
                        },
                            imageVector = Icons.Default.Close,
                            contentDescription = "Search_Icon_Close"
                        )
                    }
                },
                shape = RoundedCornerShape(10.dp),
                colors = SearchBarDefaults.colors(albumBlackBackground),
            ) {}
        }
        var ans:List<Item>?= listOf();
        if(searchResult.value.data!=null){
            ans= searchResult.value.data?.album?.albums?.items
        }
        if(ans!=null&& ans.isNotEmpty()){
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp)
            ) {
                searchResult.value.data?.album?.albums?.items.let {
                    if(it!=null){
                        items(it.size) {album->
                            AlbumCard(result!!.album!!.albums.items[album],navController)
                        }
                    }

                }

                }

        }else if(searchResult.value.isLoading){
            Text(text = "Loading")
        }
    }
}


