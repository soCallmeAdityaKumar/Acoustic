package com.example.acoustic.library.useCases

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.acoustic.common.Resource
import com.example.acoustic.data.SpotifyRepository
import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.library.data.UserPlaylists
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LibraryUseCases @Inject constructor(
    private val repository: SpotifyRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(token:String) : Flow<Resource<UserPlaylists>> = flow {
        try{
            emit(Resource.Loading(null))
            val playlists=repository.getPlayList("Bearer "+token)
            emit(Resource.Success(playlists))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred",null))
        }catch (e:Exception){
            emit(Resource.Error("Couldn't reach Server . Check your Internet Connection ",null))
        }
    }
}