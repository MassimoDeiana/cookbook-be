package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.service.IngredientService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class IngredientController(
    private val ingredientService: IngredientService
) {

    @GetMapping("/ingredients")
    fun findAll() = ingredientService.findAll()

}