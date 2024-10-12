package com.massimo.cookbookbe.repository

import com.massimo.cookbookbe.entity.Ingredients
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
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


    @Test
    fun `findAll ingredients`() {
        val ingredients = ingredientRepository.findAll()
        assert(ingredients.isEmpty())
    }

}