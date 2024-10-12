package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.entity.Ingredients
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class IngredientRepository : IngredientRepository{

    override fun findAll() = transaction {
        Ingredients.selectAll()
            .map { mapToIngredient(it) }
    }


    private fun mapToIngredient(resultRow: ResultRow) : Ingredient {
        return Ingredient(
            id = resultRow[Ingredients.id],
            name = resultRow[Ingredients.name],
            description = resultRow[Ingredients.description]
        )
    }

}