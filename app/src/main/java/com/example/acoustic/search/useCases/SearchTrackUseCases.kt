package com.example.acoustic.search.useCases

import android.net.http.HttpException
import android.os.Build
import androidx.annotation.RequiresExtension
import com.example.acoustic.common.Resource
import com.example.acoustic.data.SpotifyRepository
import com.example.acoustic.search.data.track.Tracks
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SearchTrackUseCases  @Inject constructor(
    private val repository: SpotifyRepository
) {
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(token:String,track:String) : Flow<Resource<Tracks>> = flow {
        try{
            emit(Resource.Loading(null))
            val track=repository.getSearchTrack(token= "Bearer $token",track)
            emit(Resource.Success(track))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred",null))
        }catch (e:Exception){
            emit(Resource.Error("Couldn't reach Server . Check your Internet Connection ",null))
        }
    }
}