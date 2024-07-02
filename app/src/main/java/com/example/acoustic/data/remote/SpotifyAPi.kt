package com.example.acoustic.data.remote

import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.library.data.UserPlaylists
import com.example.acoustic.search.data.album.Albums
import com.example.acoustic.search.data.track.Tracks
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface SpotifyAPi {

    @GET("/v1/me")
    suspend fun getUser(@Header("Authorization") token:String):User_Detail

    @GET("v1/me/playlists")
    suspend fun getUserPlaylists(@Header("Authorization") token:String): UserPlaylists

    @GET("v1/search")
    suspend fun getSearchAlbum(@Header("Authorization") token:String,@Query("q") albumName:String,@Query("type")type:String="album",@Query("limit")limit:Int=2,@Query("market")market:String="ES",@Query("offset")offset:Int=5):Albums

    @GET("v1/search")
    suspend fun getSearchTracks(@Header("Authorization") token:String,@Query("q") albumName:String,@Query("type")type:String="track",@Query("limit")limit:Int=2,@Query("market")market:String="ES",@Query("offset")offset:Int=5):Tracks



}