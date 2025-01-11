package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.domain.RecipeDomain
import com.massimo.cookbookbe.domain.UnitDomain
import com.massimo.cookbookbe.entity.*
import com.massimo.cookbookbe.ports.secondary.RecipeRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class RecipeRepository : RecipeRepository {

    override fun findById(recipeId: Long) = transaction {
        Recipe.leftJoin(RecipeTags)
            .leftJoin(Tags)
            .leftJoin(RecipeIngredients)
            .leftJoin(Ingredients)
            .selectAll().where { Recipe.id eq recipeId }
            .map { mapToDomain(it) }
            .firstOrNull()
    }


    private fun mapToDomain(resultRow: ResultRow): RecipeDomain {
        return RecipeDomain(
            id = resultRow[Recipe.id],
            name = resultRow[Recipe.name],
            description = resultRow[Recipe.description],
            ingredients = listOf(resultRow[Ingredients.id]).map {
                IngredientDomain(
                    id = resultRow[Ingredients.id],
                    name = resultRow[Ingredients.name],
                    description = resultRow[Ingredients.description],
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