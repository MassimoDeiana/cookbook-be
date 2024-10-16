package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
import com.massimo.cookbookbe.command.ingredient.IngredientCommands
import com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand
import com.massimo.cookbookbe.queries.ingredient.IngredientFilter
import com.massimo.cookbookbe.queries.ingredient.IngredientQueries
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class IngredientController(
    private val ingredientQueries: IngredientQueries,
    private val ingredientCommands: IngredientCommands
) {

    @GetMapping("/ingredients")
    fun findAll(@ModelAttribute ingredientFilter: IngredientFilter) = ingredientQueries.findAll(ingredientFilter)

    @GetMapping("/ingredient/{id}")
    fun findById(@PathVariable id: Long) =
        ingredientQueries.findById(id)

    @PostMapping("/ingredient")
    fun createIngredient(@RequestBody createIngredientCommand: CreateIngredientCommand) =
        ingredientCommands.handle(createIngredientCommand)

    @PutMapping("/ingredient/{id}")
    fun updateIngredient(@PathVariable id: Long, @RequestBody updateIngredientInfoCommand: UpdateIngredientInfoCommand) =
        ingredientCommands.handle(id, updateIngredientInfoCommand)


}