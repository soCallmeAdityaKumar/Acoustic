package com.example.acoustic.detail.model

import com.example.acoustic.data.dto.album.Album
import com.example.acoustic.data.dto.album.Item
import com.example.acoustic.data.dto.artist.getartist.Artist
import com.example.acoustic.data.dto.artist.artistAlbum.ArtistAlbum
import com.example.acoustic.albumCard.AlbumItem

data class DetailModel(
    val id:String?,
    val name:String?,
    val image:List<String>?,
    val artistImage:String?,
    val artistName:String?,
    val type:String?,
    val followers:Int?,
    val list:List<AlbumItem>?,
)


fun Album.toDetailModel():DetailModel{

    val image= mutableListOf<String>()

    val track= mutableListOf<AlbumItem>()
    for(i in tracks.items){
        var artists=""
        for(j in i.artists){
            artists+=j.name+","
        }
        track.add(AlbumItem(i.name,i.id,artists.dropLast(1)))
    }
    for(i in 0..track.size){
        image.add(images[0].url)
    }
    return DetailModel(
        id = id,
        name=name,
        image = image,
        artistName = artists[0].name,
        list =track,
        followers = popularity,
        type = album_type,
        artistImage = image[0]
    )
}

fun Item.toAlbumItem(): AlbumItem {
    var artistsName=artists[0].name+","+artists[1].name
    return AlbumItem(name,id,artistsName)
}
fun com.example.acoustic.data.dto.artist.artistAlbum.Item.toDetailModel():DetailModel{
    var artistsName=artists[0].name+","+artists[1].name
    val image= mutableListOf<String>()
    for(i in images){
        image.add(i.url)
    }
    return DetailModel(
        id = id,
        name = name,
        list =null,
        type = album_type,
        artistName = artistsName,
        image =image,
        followers = null,
        artistImage = null
    )
}

fun com.example.acoustic.data.dto.severalArtists.Artist.toDetailModel():DetailModel{

    return DetailModel(
        id=id,
        name=name,
        image = null,
        type = type,
        artistName = null,
        list = null,
        followers = followers.total,
        artistImage = images[0].url
    )
}


fun ArtistAlbum.toDetailModel():DetailModel{
    val list= mutableListOf<AlbumItem>()
    val image= mutableListOf<String>()
    for(i in items){
        image.add(i.images[0].url)
    }
    for(i in items){
        var artists=""
        for(j in i.artists){
            artists+=j.name.toString()+","
        }
        artists.dropLast(1)
        list.add(AlbumItem(i.name,i.id,artists))
    }
    return DetailModel(
        id=null,
        name=null,
        image =image,
        followers = null,
        artistName = null,
        list =list,
        type = null,
        artistImage = null
    )

}


fun Artist.toDetailModel():DetailModel{

    return DetailModel(
        id = id,
        name = name,
        type = type,
        followers = followers.total,
        image = null,
        list = null,
        artistName = name,
        artistImage = images[0].url
    )
}
