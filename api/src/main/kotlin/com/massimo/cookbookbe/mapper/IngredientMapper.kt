package com.massimo.cookbookbe.mapper

import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.model.CreateIngredientCommand
import com.massimo.cookbookbe.model.Ingredient
import com.massimo.cookbookbe.model.UpdateIngredientInfoCommand

interface IngredientMapper {

    fun map(ingredient: Ingredient) : IngredientDomain

    fun map(ingredientDomain: IngredientDomain): Ingredient

    fun map(createIngredientCommand: CreateIngredientCommand): com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand

    fun map(createIngredientCommand: com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand): CreateIngredientCommand

    fun map(updateIngredientInfoCommand: UpdateIngredientInfoCommand): com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand

    fun map(updateIngredientInfoCommand: com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand): UpdateIngredientInfoCommand



}