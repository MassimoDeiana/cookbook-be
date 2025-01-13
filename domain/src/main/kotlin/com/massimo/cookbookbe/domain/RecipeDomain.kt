package com.massimo.cookbookbe.domain

data class RecipeDomain(
    val id: Long?,
    val name: String,
    val ingredients: List<IngredientDomain>,
    val steps: List<String>,
    val category: CategoryDomain
)