package com.example.acoustic.data

import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.data.remote.SpotifyAPi
import com.example.acoustic.library.data.UserPlaylists
import com.example.acoustic.search.data.album.Albums
import com.example.acoustic.search.data.track.Tracks
import javax.inject.Inject


interface SpotifyRepository  {

    suspend fun getUser(token:String):User_Detail

    suspend fun getPlayList(token: String): UserPlaylists

    suspend fun getsearchAlbum(token:String,album:String):Albums

    suspend fun  getSearchTrack(token: String,track:String):Tracks
}