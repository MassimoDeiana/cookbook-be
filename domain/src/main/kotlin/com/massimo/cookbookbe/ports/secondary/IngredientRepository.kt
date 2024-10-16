package com.massimo.cookbookbe.ports.secondary

import com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
import com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.queries.ingredient.IngredientFilter

interface IngredientRepository {

    fun findAll(ingredientFilter: IngredientFilter) : List<Ingredient>

    fun findById(ingredientId: Long): Ingredient?

    fun save(createIngredientCommand: CreateIngredientCommand) : Long

    fun delete(ingredientId: Long) : Boolean

    fun update(id: Long, updateIngredientInfoCommand: UpdateIngredientInfoCommand) : Boolean

}