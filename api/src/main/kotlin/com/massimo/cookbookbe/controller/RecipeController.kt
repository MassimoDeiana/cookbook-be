package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.ports.primary.RecipeService
import org.springframework.web.bind.annotation.RestController

@RestController
class RecipeController(
    private val recipeService: RecipeService,
) {



}