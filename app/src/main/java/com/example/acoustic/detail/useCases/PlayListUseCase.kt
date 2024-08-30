package com.example.acoustic.detail.useCases

import android.net.http.HttpException
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresExtension
import com.example.acoustic.common.Resource
import com.example.acoustic.data.SpotifyRepository
import com.example.acoustic.detail.model.DetailModel
import com.example.acoustic.detail.model.toDetailModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PlayListUseCase @Inject constructor (
    private val repository: SpotifyRepository
){
    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    operator fun invoke(token:String,id:String) : Flow<Resource<DetailModel>> = flow {
        try{
            emit(Resource.Loading(null))
            val playList=repository.getPlayList("Bearer $token",id)
            Log.d("getPlayList",playList.toString())
            emit(Resource.Success(playList.toDetailModel()))
        }catch (e: HttpException){
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred",null))
        }catch (e:Exception){
            emit(Resource.Error("Couldn't reach Server . Check your Internet Connection ",null))
        }
    }
}