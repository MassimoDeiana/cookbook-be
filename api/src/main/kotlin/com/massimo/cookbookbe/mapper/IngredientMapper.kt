package com.massimo.cookbookbe.mapper

import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.domain.UnitDomain
import com.massimo.cookbookbe.model.CreateIngredientCommand
import com.massimo.cookbookbe.model.Ingredient
import com.massimo.cookbookbe.model.UpdateIngredientInfoCommand

class IngredientMapper {

    fun map(ingredient: Ingredient) = IngredientDomain(
        id = ingredient.id!!,
        name = ingredient.name!!,
        description = ingredient.description!!,
        unit = UnitDomain.valueOf(ingredient.unit!!.value),
        category = CategoryDomain.valueOf(ingredient.category!!.value),
        quantityInStock = ingredient.quantityInStock!!
    )

    fun map(ingredientDomain: IngredientDomain) = Ingredient(

    )

//    fun map(ingredientDomain: IngredientDomain): Ingredient
//
//    fun map(createIngredientCommand: CreateIngredientCommand): com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
//
//    fun map(createIngredientCommand: com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand): CreateIngredientCommand
//
//    fun map(updateIngredientInfoCommand: UpdateIngredientInfoCommand): com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand
//
//    fun map(updateIngredientInfoCommand: com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand): UpdateIngredientInfoCommand
//


}