package com.massimo.cookbookbe.ports.secondary

import com.massimo.cookbookbe.domain.RecipeDomain

interface RecipeRepository {

    fun findById(recipeId: Long): RecipeDomain?
}