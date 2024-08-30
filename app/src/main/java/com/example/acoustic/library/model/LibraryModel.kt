package com.example.acoustic.library.model

import com.example.acoustic.data.dto.user_saved_playlists.Item

data class LibraryModel(
    val name:String?=null,
    val image:String?=null,
    val id:String?=null,
    val type:String?=null,
)

fun Item.toLibraryModel():LibraryModel{
    var img=""
    if(images.isNullOrEmpty()){
        img="https://i.scdn.co/image/ab67616d00001e023154f0bdf9a17385d7afc6ba"
    }else{
        img=images[0].url
    }
    return LibraryModel(name = name, id = id, image = img, type = type)
}




