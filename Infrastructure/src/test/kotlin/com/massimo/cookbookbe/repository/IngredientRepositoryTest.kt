package com.massimo.cookbookbe.repository

import assertk.assertThat
import assertk.assertions.isEmpty
import assertk.assertions.isEqualTo
import assertk.assertions.isNotEmpty
import com.massimo.cookbookbe.domain.Category
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.domain.Unit
import com.massimo.cookbookbe.entity.Ingredients
import org.instancio.Instancio
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.deleteAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest


const val INGREDIENT_ID = 1

@SpringBootTest(classes = [IngredientRepository::class])
class IngredientRepositoryTest() {

    @Autowired
    lateinit var ingredientRepository: IngredientRepository

    @BeforeEach
    fun setup() {
        Database.connect("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;", driver = "org.h2.Driver")
        transaction {
            SchemaUtils.create(Ingredients)
            addIngredientIntoDb()
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
        transaction {
            Ingredients.deleteAll()
        }
        val ingredients = ingredientRepository.findAll()
        assertThat(ingredients).isEmpty()
    }

    @Test
    fun `save should return ingredient id`() {
        transaction {
            Ingredients.deleteAll()
        }
        val ingredient = ingredient()
        val id = ingredientRepository.save(ingredient)
        assertThat(id).isEqualTo(2)
    }

    @Test
    fun `findAll should return list of ingredients`() {
        val ingredients = ingredientRepository.findAll()
        assertThat(ingredients).isNotEmpty()
    }

    @Test
    fun `findById should return ingredient with matching id`() {
        val foundIngredient = ingredientRepository.findById(INGREDIENT_ID)
        assertThat(foundIngredient.id).isEqualTo(INGREDIENT_ID)
    }

    @Test
    fun `delete should remove ingredient from db`() {
        ingredientRepository.delete(INGREDIENT_ID)
        val ingredients = ingredientRepository.findAll()
        assertThat(ingredients).isEmpty()
    }

    @Test
    fun `update should update ingredient in db`() {
        val ingredient = Ingredient(INGREDIENT_ID, "Updated Ingredient", "Updated Description", Unit.GRAM, Category.MEAT)
        val isIngredientUpdated = ingredientRepository.update(ingredient)
        assertThat(isIngredientUpdated).isEqualTo(true)
    }

    private fun addIngredientIntoDb() {
        val ingredient = ingredient()
        ingredientRepository.save(ingredient)
    }

    fun ingredient(): Ingredient = Instancio.of(Ingredient::class.java).create()

}