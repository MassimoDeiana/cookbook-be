package com.massimo.cookbookbe.queries.recipe

import com.massimo.cookbookbe.ports.secondary.RecipeRepository
import org.springframework.stereotype.Service

@Service
class RecipeQueries(
    private val recipeRepository: RecipeRepository
) {

    fun findById(id: Long) =
        recipeRepository.findById(id)
}