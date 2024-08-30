package com.example.acoustic.data.dto.user_saved_playlists

data class User_Playlists(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)