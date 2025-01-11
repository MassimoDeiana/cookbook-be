package com.massimo.cookbookbe.domain

data class IngredientDomain(
    val id: Long?,
    val name: String,
    val description: String,
    val unit: UnitDomain,
    val category: CategoryDomain,
    val quantityInStock: Int
)
