package com.example.acoustic.data.dto.artist.artistAlbum

data class ArtistAlbum(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)