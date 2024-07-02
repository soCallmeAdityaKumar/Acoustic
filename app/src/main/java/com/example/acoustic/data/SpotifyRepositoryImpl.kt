package com.example.acoustic.data

import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.data.remote.SpotifyAPi
import com.example.acoustic.library.data.UserPlaylists
import com.example.acoustic.search.data.album.Albums
import com.example.acoustic.search.data.track.Tracks
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(private val spotifyAPi: SpotifyAPi):SpotifyRepository {

    override suspend fun getUser(token: String): User_Detail {
        return spotifyAPi.getUser(token)
    }

    override suspend fun getPlayList(token: String): UserPlaylists {
        return spotifyAPi.getUserPlaylists(token)
    }

    override suspend fun getsearchAlbum(token:String,album: String): Albums {
        return spotifyAPi.getSearchAlbum(token,album)
    }

    override suspend fun getSearchTrack(token: String, track: String): Tracks {
        return spotifyAPi.getSearchTracks(token,track)
    }

}