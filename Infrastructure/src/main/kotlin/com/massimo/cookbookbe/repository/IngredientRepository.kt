package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.domain.Category
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.domain.Unit
import com.massimo.cookbookbe.entity.Categories
import com.massimo.cookbookbe.entity.Ingredients
import com.massimo.cookbookbe.entity.Units
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class IngredientRepository : IngredientRepository{

    override fun findAll() = transaction {
        Ingredients.selectAll()
            .map { mapToDomain(it) }
    }

    override fun save(ingredient: Ingredient) = transaction {
        Ingredients.insert {
            it[name] = ingredient.name
            it[description] = ingredient.description
            it[unit] = Units.valueOf(ingredient.unit.name)
            it[category] = Categories.valueOf(ingredient.category.name)
        } get Ingredients.id
    }

    override fun findById(ingredientId: Long) = transaction {
        Ingredients.selectAll()
            .where { Ingredients.id eq ingredientId }
            .map { mapToDomain(it) }
            .firstOrNull()
    }

    override fun delete(ingredientId: Long) = transaction {
        val rowDeleted = Ingredients.deleteWhere{ id eq ingredientId }
        rowDeleted > 0
    }

    override fun update(ingredient: Ingredient) = transaction {
        val rowUpdated = Ingredients.update({ Ingredients.id eq ingredient.id }) {
            it[name] = ingredient.name
            it[description] = ingredient.description
            it[unit] = Units.valueOf(ingredient.unit.name)
            it[category] = Categories.valueOf(ingredient.category.name)
        }
        rowUpdated > 0
    }

    private fun mapToDomain(resultRow: ResultRow) : Ingredient {
        return Ingredient(
            id = resultRow[Ingredients.id],
            name = resultRow[Ingredients.name],
            description = resultRow[Ingredients.description],
            unit = Unit.valueOf(resultRow[Ingredients.unit].name),
            category = Category.valueOf(resultRow[Ingredients.category].name)
        )
    }


}