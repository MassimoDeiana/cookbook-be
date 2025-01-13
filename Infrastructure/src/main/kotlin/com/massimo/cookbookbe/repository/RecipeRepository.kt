package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.domain.RecipeDomain
import com.massimo.cookbookbe.domain.UnitDomain
import com.massimo.cookbookbe.entity.*
import com.massimo.cookbookbe.ports.secondary.RecipeRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class RecipeRepository : RecipeRepository {

    override fun findById(recipeId: Long) = transaction {
        Recipe.leftJoin(RecipeTags) { Recipe.id eq RecipeTags.recipeId }
            .leftJoin(Tags) { RecipeTags.tagId eq Tags.id }
            .leftJoin(RecipeIngredients) { Recipe.id eq RecipeIngredients.recipeId }
            .leftJoin(Ingredients) { RecipeIngredients.ingredientId eq Ingredients.id }
            .selectAll().where { Recipe.id eq recipeId }
            .map { mapToDomain(it) }
            .firstOrNull()
    }

    override fun create(recipe: RecipeDomain): Long = transaction {
            insertRecipe(recipe)

        }


    private fun insertRecipe(recipe: RecipeDomain) = Recipe.insert {
            it[name] = recipe.name
            it[category] = Categories.valueOf(recipe.category.name)
        } get Recipe.id





    private fun mapToDomain(resultRow: ResultRow): RecipeDomain {
        return RecipeDomain(
            id = resultRow[Recipe.id],
            name = resultRow[Recipe.name],
            ingredients = listOf(resultRow[Ingredients.id]).map {
                IngredientDomain(
                    id = resultRow[Ingredients.id],
                    name = resultRow[Ingredients.name],
                    unit = UnitDomain.valueOf(resultRow[Ingredients.unit].name),
                    category = CategoryDomain.valueOf(resultRow[Ingredients.category].name),
                    quantityInStock = resultRow[Ingredients.quantityInStock]
                )
            },
            steps = listOf(resultRow[Recipe.instructions]),
            category = CategoryDomain.valueOf(resultRow[Recipe.category].name)
        )
    }
}