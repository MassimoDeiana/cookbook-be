package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.domain.UnitDomain
import com.massimo.cookbookbe.entity.Categories
import com.massimo.cookbookbe.entity.Ingredients
import com.massimo.cookbookbe.entity.Units
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import com.massimo.cookbookbe.domain.IngredientFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository

@Repository
class IngredientRepository : IngredientRepository{

    override fun findAll(filter: IngredientFilter) = transaction {
        val order = if (filter.order == "asc") SortOrder.ASC else SortOrder.DESC
        Ingredients.selectAll()
            .apply {
                filter.name?.let { andWhere { Ingredients.name like "%$it%" } }
                filter.category?.let { andWhere { Ingredients.category eq Categories.valueOf(it.name) } }
                filter.orderBy?.let { orderByColumn ->
                    val column = Ingredients.columns.firstOrNull { it.name == orderByColumn }
                    column?.let { orderBy(it, order) }
                }
            }
            .map { mapToDomain(it) }
    }

    override fun save(ingredient: IngredientDomain) = transaction {
        Ingredients.insert {
            it[name] = ingredient.name
            it[description] = ingredient.description
            it[unit] = Units.valueOf(ingredient.unit.name)
            it[category] = Categories.valueOf(ingredient.category.name)
        } get Ingredients.id
    }

    override fun findById(id: Long) = transaction {
        Ingredients.selectAll()
            .where { Ingredients.id eq id }
            .map { mapToDomain(it) }
            .firstOrNull()
    }

    override fun delete(id: Long) = transaction {
        val rowDeleted = Ingredients.deleteWhere{ this.id eq id }
        rowDeleted > 0
    }

    override fun update(id: Long, ingredient: IngredientDomain) = transaction {
        val rowUpdated = Ingredients.update({ Ingredients.id eq id }) {
            it[name] = ingredient.name
            it[description] = ingredient.description
            it[unit] = Units.valueOf(ingredient.unit.name)
            it[category] = Categories.valueOf(ingredient.category.name)
        }
        rowUpdated > 0
    }


    private fun mapToDomain(resultRow: ResultRow) : IngredientDomain {
        return IngredientDomain(
            id = resultRow[Ingredients.id],
            name = resultRow[Ingredients.name],
            description = resultRow[Ingredients.description],
            unit = UnitDomain.valueOf(resultRow[Ingredients.unit].name),
            category = CategoryDomain.valueOf(resultRow[Ingredients.category].name),
            quantityInStock = resultRow[Ingredients.quantityInStock]
        )
    }


}