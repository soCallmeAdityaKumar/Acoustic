package com.example.acoustic.common

object Constants{
    const val SPOTIFY_BASE_URL="https://api.spotify.com"
}

enum class TYPE{
    ALBUM,
    ARTIST,
    GENRE,
    CATEGORIES,
    LIBRARY,
    PLAYLIST
}

enum class SHARED_PREF{
    USER_PHONE,
    USER_TOKEN

}