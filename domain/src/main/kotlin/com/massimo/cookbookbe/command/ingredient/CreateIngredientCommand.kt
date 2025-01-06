package com.massimo.cookbookbe.command.ingredient

import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.domain.UnitDomain

data class CreateIngredientCommand(
    val name: String,
    val description: String,
    val unit: UnitDomain,
    val category: CategoryDomain
)
