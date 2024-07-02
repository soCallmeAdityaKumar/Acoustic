package com.example.acoustic.search.useCases

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.acoustic.common.Resource
import com.example.acoustic.data.SpotifyRepository
import com.example.acoustic.library.data.UserPlaylists
import com.example.acoustic.search.data.album.Albums
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchAlbumUseCases @Inject constructor(
    private val repository: SpotifyRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(token:String,album:String) : Flow<Resource<Albums>> = flow {
        try{
            emit(Resource.Loading(null))
            val albums=repository.getsearchAlbum(token= "Bearer $token",album=album)
            emit(Resource.Success(albums))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred",null))
        }catch (e:Exception){
            emit(Resource.Error("Couldn't reach Server . Check your Internet Connection ",null))
        }
    }
}