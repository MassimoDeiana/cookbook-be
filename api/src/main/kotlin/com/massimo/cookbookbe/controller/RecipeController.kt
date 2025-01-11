package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.queries.recipe.RecipeQueries
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class RecipeController(
    private val recipeQueries: RecipeQueries,
) {

    @GetMapping("/recipes/{recipeId}")
    fun findById(@PathVariable recipeId: Long) = recipeQueries.findById(recipeId)


}