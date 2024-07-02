package com.example.acoustic.library.data

data class UserPlaylists(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)