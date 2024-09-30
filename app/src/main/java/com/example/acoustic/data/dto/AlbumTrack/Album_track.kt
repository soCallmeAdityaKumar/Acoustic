package com.example.acoustic.data.dto.AlbumTrack

data class Album_track(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: Any,
    val offset: Int,
    val previous: Any,
    val total: Int
)