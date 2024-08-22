package com.example.acoustic.data.remote

import com.example.acoustic.data.dto.artist.getartist.Artist
import com.example.acoustic.data.dto.artist.artistAlbum.ArtistAlbum
import com.example.acoustic.data.dto.severalArtists.Artists
import com.example.acoustic.data.dto.categories.Categories
import com.example.acoustic.data.dto.new_releases.Releases
import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.library.data.UserPlaylists
import com.example.acoustic.search.data.album.Album
import com.example.acoustic.search.data.track.Tracks
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface SpotifyAPi {

    @GET("/v1/me")
    suspend fun getUser(@Header("Authorization") token:String):User_Detail

    @GET("v1/me/playlists")
    suspend fun getUserPlaylists(@Header("Authorization") token:String): UserPlaylists

    @GET("v1/search")
    suspend fun getSearchAlbum(@Header("Authorization") token:String,@Query("q") albumName:String,@Query("type")type:String="album"): Album

    @GET("v1/search")
    suspend fun getSearchTracks(@Header("Authorization") token:String,@Query("q") trackName:String,@Query("type")type:String="track",@Query("limit")limit:Int=2,@Query("market")market:String="ES",@Query("offset")offset:Int=5):Tracks

    @GET("v1/artists")
    suspend fun getSeveralArtists(@Header("Authorization") token:String,@Query("ids")id:List<String> = listOf("2CIMQHirSU0MQqyYHq0eOx","57dN52uHvrHOxijzpIgu3E","1vCWHaC5f2uS3yhpwWbIA6")):Artists

    @GET("v1/artists/{id}/albums")
    suspend fun getArtistAlbum(@Header("Authorization") token:String,@Path("id")id:String): ArtistAlbum


    @GET("v1/browse/categories")
    suspend fun getSeveralCategories(@Header("Authorization")token:String,@Query("locale")locale:String="sv_SE", @Query("limit")limit:Int=50, @Query("offset")offset: Int=5):Categories


    @GET("v1/browse/new-releases")//New Album
    suspend fun getNewReleases(@Header("Authorization")token:String, @Query("offset")offset: Int=5):Releases


    @GET("v1/albums/{id}")
    suspend fun getAlbum(@Header("Authorization")token:String, @Path("id")albumId:String):com.example.acoustic.data.dto.album.Album

    //Artists
    @GET("v1/artists/{id}")
    suspend fun getArtist(@Header("Authorization")token:String, @Path("id")artistId:String): Artist

    @GET("v1/artists/{id}/top-tracks")
    suspend fun getArtistTopTracks(@Header("Authorization")token:String, @Path("id")artistId:String): Artist


    @GET("v1/browse/categories/{category_id}")
    suspend fun getSingleCategories(@Header("Authorization")token:String, @Path("category_id")id:String): Artist



}