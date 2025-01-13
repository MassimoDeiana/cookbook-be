package com.massimo.cookbookbe.service

import com.massimo.cookbookbe.domain.RecipeDomain
import com.massimo.cookbookbe.ports.primary.RecipeService
import com.massimo.cookbookbe.ports.secondary.RecipeRepository
import org.springframework.stereotype.Service

@Service
class RecipeService(
    private val recipeRepository: RecipeRepository
) : RecipeService {

    fun create(recipe: RecipeDomain): Long {
        val recipeId = recipeRepository.create(recipe)

        return recipeId
    }

}