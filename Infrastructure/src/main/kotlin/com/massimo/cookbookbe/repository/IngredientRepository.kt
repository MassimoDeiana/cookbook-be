package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
import com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand
import com.massimo.cookbookbe.domain.Category
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.domain.Unit
import com.massimo.cookbookbe.entity.Categories
import com.massimo.cookbookbe.entity.Ingredients
import com.massimo.cookbookbe.entity.Units
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import com.massimo.cookbookbe.queries.ingredient.IngredientFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class IngredientRepository : IngredientRepository{

    override fun findAll(ingredientFilter: IngredientFilter) = transaction {
        val order = if (ingredientFilter.order == "asc") SortOrder.ASC else SortOrder.DESC
        Ingredients.selectAll()
            .apply {
                ingredientFilter.name?.let { andWhere { Ingredients.name like "%$it%" } }
                ingredientFilter.category?.let { andWhere { Ingredients.category eq Categories.valueOf(it) } }
                ingredientFilter.orderBy?.let { orderByColumn ->
                    val column = Ingredients.columns.firstOrNull { it.name == orderByColumn }
                    column?.let { orderBy(it, order) }
                }
            }
            .map { mapToDomain(it) }
    }

    override fun save(createIngredientCommand: CreateIngredientCommand) = transaction {
        Ingredients.insert {
            it[name] = createIngredientCommand.name
            it[description] = createIngredientCommand.description
            it[unit] = Units.valueOf(createIngredientCommand.unit.name)
            it[category] = Categories.valueOf(createIngredientCommand.category.name)
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

    override fun update(id: Long, updateIngredientInfoCommand: UpdateIngredientInfoCommand) = transaction {
        val rowUpdated = Ingredients.update({ Ingredients.id eq id }) {
            it[name] = updateIngredientInfoCommand.name
            it[description] = updateIngredientInfoCommand.description
            it[unit] = Units.valueOf(updateIngredientInfoCommand.unit.name)
            it[category] = Categories.valueOf(updateIngredientInfoCommand.category.name)
        }
        rowUpdated > 0
    }


    private fun mapToDomain(resultRow: ResultRow) : Ingredient {
        return Ingredient(
            id = resultRow[Ingredients.id],
            name = resultRow[Ingredients.name],
            description = resultRow[Ingredients.description],
            unit = Unit.valueOf(resultRow[Ingredients.unit].name),
            category = Category.valueOf(resultRow[Ingredients.category].name),
            quantityInStock = resultRow[Ingredients.quantityInStock]
        )
    }


}