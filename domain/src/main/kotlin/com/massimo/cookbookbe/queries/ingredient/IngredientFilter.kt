package com.massimo.cookbookbe.queries.ingredient

import com.massimo.cookbookbe.domain.CategoryDomain

data class IngredientFilter(
    val name: String? = null,
    val category: CategoryDomain? = null,
    val orderBy: String? = null,
    val order: String? = null

)
