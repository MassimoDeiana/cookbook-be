package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table

object RecipeTags: Table() {
    val recipeId = long("recipe_id").references(Recipe.id)
    val tagId = long("tag_id").references(Tags.id)

    override val primaryKey = PrimaryKey(recipeId, tagId, name = "PK_RecipeTag_ID")
}