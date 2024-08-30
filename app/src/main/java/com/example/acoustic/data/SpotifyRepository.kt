package com.example.acoustic.data

import com.example.acoustic.data.dto.artist.getartist.Artist
import com.example.acoustic.data.dto.artist.artistAlbum.ArtistAlbum
import com.example.acoustic.data.dto.severalArtists.Artists
import com.example.acoustic.data.dto.categories.Categories
import com.example.acoustic.data.dto.new_releases.Releases
import com.example.acoustic.data.dto.playlist.PlayList
import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.data.dto.user_saved_playlists.User_Playlists
import com.example.acoustic.search.data.album.Album
import com.example.acoustic.search.data.track.Tracks


interface SpotifyRepository  {

    //USER
    suspend fun getUser(token:String):User_Detail

    suspend fun getUserSavedPlayList(token: String,id: String): User_Playlists

    //SEARCH
    suspend fun getsearchAlbum(token:String,album:String): Album

    suspend fun  getSearchTrack(token: String,track:String):Tracks


    //ARTISTS
    suspend fun getSeveralArtists(token: String,id:List<String>):Artists

    suspend fun getArtist(token: String,id:String): Artist

    suspend fun getArtistAlbum(token: String,id:String): ArtistAlbum



    suspend fun getSeveralCategories(token: String):Categories

    suspend fun getNewReleasesAlbum(token: String):Releases



    //ALBUM
    suspend fun getForAlbum(token: String,id:String):com.example.acoustic.data.dto.album.Album


    //PLAYLIST
    suspend fun getPlayList(token: String,id:String):PlayList



}