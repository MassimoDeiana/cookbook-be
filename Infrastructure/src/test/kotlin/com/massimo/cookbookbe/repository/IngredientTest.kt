package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.entity.Ingredients
import org.instancio.Instancio
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest(classes = [IngredientRepository::class])
class IngredientTest() {

    @Autowired
    lateinit var ingredientRepository: IngredientRepository

    @BeforeEach
    fun setup() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(Ingredients)
        }
    }

    @AfterEach
    fun tearDown() {
        transaction {
            SchemaUtils.drop(Ingredients)
        }
    }


    @Test
    fun `findAll should return empty list of ingredients when ingredients is empty`() {
        val ingredients = ingredientRepository.findAll()
        assert(ingredients.isEmpty())
    }

    @Test
    fun `save should return ingredient id`() {
        val ingredient = ingredient()
        val id = ingredientRepository.save(ingredient)
        assert(id == 1)
    }

    @Test
    fun `findAll should return list of ingredients`() {
        val ingredient = ingredient()
        ingredientRepository.save(ingredient)
        val ingredients = ingredientRepository.findAll()
        assert(ingredients.isNotEmpty())
    }


    fun ingredient(): Ingredient = Instancio.of(Ingredient::class.java).create()

}