package com.massimo.cookbookbe.queries.ingredient

data class IngredientFilter(
    val name: String? = null,
    val category: String? = null,
    val orderBy: String? = null,
    val order: String? = null

)
