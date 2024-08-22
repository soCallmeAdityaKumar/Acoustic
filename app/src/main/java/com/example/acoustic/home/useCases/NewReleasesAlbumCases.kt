package com.example.acoustic.home.useCases

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.acoustic.common.Resource
import com.example.acoustic.data.SpotifyRepository
import com.example.acoustic.data.dto.new_releases.Releases
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewReleasesAlbumCases@Inject constructor (
    private val repository: SpotifyRepository
){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(token:String) : Flow<Resource<Releases>> = flow {
        try{
            emit(Resource.Loading(null))
            val artists=repository.getNewReleasesAlbum("Bearer $token")
            emit(Resource.Success(artists))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred",null))
        }catch (e:Exception){
            emit(Resource.Error("Couldn't reach Server . Check your Internet Connection ",null))
        }
    }
}