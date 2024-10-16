package com.massimo.cookbookbe.command.ingredient

import com.massimo.cookbookbe.domain.Category
import com.massimo.cookbookbe.domain.Unit

data class CreateIngredientCommand(
    val name: String,
    val description: String,
    val unit: Unit,
    val category: Category
)
