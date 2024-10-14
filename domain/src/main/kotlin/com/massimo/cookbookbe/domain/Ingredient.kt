package com.massimo.cookbookbe.domain

data class Ingredient(
    val id: Long,
    val name: String,
    val description: String,
    val unit: Unit,
    val category: Category
)
