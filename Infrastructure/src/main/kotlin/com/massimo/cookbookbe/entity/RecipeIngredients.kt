package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table

object RecipeIngredients : Table() {
    val recipeId = long("recipe_id").references(Recipe.id)
    val ingredientId = long("ingredient_id").references(Ingredients.id)
    val quantity = integer("quantity")
    val unit = enumerationByName<Units>("unit", 50)

    override val primaryKey = PrimaryKey(recipeId, ingredientId, name = "PK_RecipeIngredient_ID")
}