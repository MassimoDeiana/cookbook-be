package com.massimo.cookbookbe.service

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.hasSize
import assertk.assertions.isEqualTo
import assertk.assertions.isInstanceOf
import assertk.assertions.isTrue
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import io.mockk.every
import io.mockk.mockk
import org.instancio.Instancio
import org.instancio.Select.field
import org.junit.jupiter.api.Test

class IngredientServiceTest {

    private var ingredientRepository: IngredientRepository
    private var ingredientService : IngredientService

    init {
        ingredientRepository = mockk()
        ingredientService = IngredientService(ingredientRepository)
    }

    companion object {
        private const val INGREDIENT_ID = 1L
    }

    @Test
    fun `findAll should return all ingredients`() {
        every { ingredientRepository.findAll() } returns listOf(ingredient(), ingredient())

        val actualResult = ingredientService.findAll()

        assertThat(actualResult).hasSize(2)
    }

    @Test
    fun `findById should return an ingredient with matching id`() {
        val expectedIngredient = ingredient()
        every { ingredientRepository.findById(any()) } returns expectedIngredient

        val actualIngredient = ingredientService.findById(INGREDIENT_ID)

        assertThat(actualIngredient.id).isEqualTo(expectedIngredient.id)
    }

    @Test
    fun `findById should throw IngredientNotFoundException when ingredient is not found`() {
        every { ingredientRepository.findById(any()) } returns null

        assertFailure {
            ingredientService.findById(INGREDIENT_ID)
        }.isInstanceOf(IngredientNotFoundException::class)
    }

    @Test
    fun `save should return the id of the created ingredient`() {
        every { ingredientRepository.save(any()) } returns INGREDIENT_ID

        val actualIngredientId = ingredientService.save(ingredient())

        assertThat(actualIngredientId).isEqualTo(INGREDIENT_ID)
    }

    @Test
    fun `delete should return true when the element has been deleted`() {
        every { ingredientRepository.delete(any()) } returns true

        val isIngredientDeleted = ingredientService.delete(INGREDIENT_ID)

        assertThat(isIngredientDeleted).isTrue()
    }

    @Test
    fun `update should return true when the element has been updated`() {
        every { ingredientRepository.update(any()) } returns true

        val isIngredientUpdated = ingredientService.update(ingredient())

        assertThat(isIngredientUpdated).isTrue()
    }

    private fun ingredient() : Ingredient {
        return Instancio.of(Ingredient::class.java)
            .set(field("id"), 1)
            .create()
    }


}