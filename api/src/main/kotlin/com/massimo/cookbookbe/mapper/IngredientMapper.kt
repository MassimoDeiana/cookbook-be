package com.massimo.cookbookbe.mapper

import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.domain.UnitDomain
import com.massimo.cookbookbe.model.*
import com.massimo.cookbookbe.model.Unit
import org.springframework.stereotype.Component

@Component
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
        id = ingredientDomain.id,
        name = ingredientDomain.name,
        description = ingredientDomain.description,
        unit = Unit.valueOf(ingredientDomain.unit.name),
        category = Category.valueOf(ingredientDomain.category.name),
        quantityInStock = ingredientDomain.quantityInStock
    )

    fun map(createIngredientCommand: CreateIngredientCommand) = IngredientDomain(
        name = createIngredientCommand.name,
        description = createIngredientCommand.description,
        unit = UnitDomain.valueOf(createIngredientCommand.unit.value),
        category = CategoryDomain.valueOf(createIngredientCommand.category.value),
        quantityInStock = 0
    )

    fun map(updateIngredientInfoCommand: UpdateIngredientInfoCommand) = IngredientDomain(
        name = updateIngredientInfoCommand.name!!,
        description = updateIngredientInfoCommand.description!!,
        unit = UnitDomain.valueOf(updateIngredientInfoCommand.unit!!.value),
        category = CategoryDomain.valueOf(updateIngredientInfoCommand.category!!.value),
        quantityInStock = updateIngredientInfoCommand.quantityInStock!!
    )




}