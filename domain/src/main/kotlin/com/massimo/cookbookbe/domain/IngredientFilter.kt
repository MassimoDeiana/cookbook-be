package com.massimo.cookbookbe.domain

data class IngredientFilter(
    val name: String? = null,
    val category: CategoryDomain? = null,
    val orderBy: String? = null,
    val order: String? = null

)
