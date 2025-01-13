package com.massimo.cookbookbe

import com.massimo.cookbookbe.entity.*
import com.massimo.cookbookbe.ports.secondary.DatabaseUtils
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class DatabaseUtils : DatabaseUtils {

    override fun refreshDatabase() {
        transaction {
            SchemaUtils.drop(RecipeIngredients)
            SchemaUtils.drop(Tags)
            SchemaUtils.drop(Recipe)
            SchemaUtils.drop(Ingredients)

            SchemaUtils.create(Ingredients)
            SchemaUtils.create(Recipe)
            SchemaUtils.create(RecipeIngredients)
            SchemaUtils.create(Tags)

            Ingredients.insert {
                it[name] = "Tomato"
                it[unit] = Units.GRAM
                it[category] = Categories.VEGETABLE
                it[quantityInStock] = 1000
            }

            Ingredients.insert {
                it[name] = "Pasta"
                it[unit] = Units.GRAM
                it[category] = Categories.CEREAL
                it[quantityInStock] = 1000
            }

            Ingredients.insert {
                it[name] = "Salt"
                it[unit] = Units.GRAM
                it[category] = Categories.SPICE
                it[quantityInStock] = 1000
            }

            Recipe.insert {
                it[name] = "Pasta with tomato sauce"
                it[instructions] = "Boil pasta, add tomato sauce"
                it[category] = Categories.CEREAL
            }

            RecipeIngredients.insert {
                it[recipeId] = 1
                it[ingredientId] = 1
                it[quantity] = 100
            }

            RecipeIngredients.insert {
                it[recipeId] = 1
                it[ingredientId] = 2
                it[quantity] = 200
            }



        }
        println("Database refreshed")
    }

}