package com.example.acoustic.data.dto.categories

data class CategoriesX(
    val href: String,
    val items: List<Item>,
    val limit: Int,
    val next: String,
    val offset: Int,
    val previous: Any,
    val total: Int
)