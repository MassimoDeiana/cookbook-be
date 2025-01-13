package com.massimo.cookbookbe.domain

data class IngredientDomain(
    val id: Long? = null,
    val name: String,
    val unit: UnitDomain,
    val category: CategoryDomain,
    val quantityInStock: Int
)
