package com.example.acoustic.data

import com.example.acoustic.data.dto.artist.getartist.Artist
import com.example.acoustic.data.dto.artist.artistAlbum.ArtistAlbum
import com.example.acoustic.data.dto.severalArtists.Artists
import com.example.acoustic.data.dto.categories.Categories
import com.example.acoustic.data.dto.new_releases.Releases
import com.example.acoustic.data.dto.playlist.PlayList
import com.example.acoustic.data.dto.track.Track
import com.example.acoustic.data.dto.user.User_Detail
import com.example.acoustic.data.dto.user_saved_playlists.User_Playlists
import com.example.acoustic.data.remote.SpotifyAPi
import com.example.acoustic.search.data.album.Album
import com.example.acoustic.search.data.track.Tracks
import javax.inject.Inject

class SpotifyRepositoryImpl @Inject constructor(private val spotifyAPi: SpotifyAPi):SpotifyRepository {

    override suspend fun getUser(token: String): User_Detail {
        return spotifyAPi.getUser(token)
    }

    override suspend fun getUserSavedPlayList(token: String,id:String): User_Playlists {
        return spotifyAPi.getUserPlaylists(token,id)
    }

    override suspend fun getsearchAlbum(token:String,album: String): Album {
        return spotifyAPi.getSearchAlbum(token, albumName = album)
    }

    override suspend fun getSearchTrack(token: String, track: String): Tracks {
        return spotifyAPi.getSearchTracks(token,track)
    }

    override suspend fun getSeveralArtists(token: String, id: List<String>): Artists {
        return spotifyAPi.getSeveralArtists(token,id)
    }

    override suspend fun getSeveralCategories(token: String): Categories {
        return spotifyAPi.getSeveralCategories(token)
    }

    override suspend fun getNewReleasesAlbum(token: String): Releases {
        return spotifyAPi.getNewReleases(token)
    }

    override suspend fun getForAlbum(token: String, id: String): com.example.acoustic.data.dto.album.Album {
        return spotifyAPi.getAlbum(token,id)
    }

    override suspend fun getPlayList(token: String, id: String): PlayList {
        return spotifyAPi.getPlayList(token,id)
    }

    override suspend fun getTrack(token: String, id: String): Track {
        return spotifyAPi.getTrack(token,id)
    }

    override suspend fun getArtistAlbum(token: String, id: String): ArtistAlbum {
        return spotifyAPi.getArtistAlbum(token,id)
    }

    override suspend fun getArtist(token: String, id: String): Artist {
        return spotifyAPi.getArtist(token,id)
    }


}