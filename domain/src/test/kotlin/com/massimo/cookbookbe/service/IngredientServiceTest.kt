package com.massimo.cookbookbe.service

import assertk.assertThat
import assertk.assertions.hasSize
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import io.mockk.every
import io.mockk.mockk
import org.instancio.Instancio
import org.junit.jupiter.api.Test

class IngredientServiceTest {

    private var ingredientRepository: IngredientRepository
    private var ingredientService : IngredientService

    init {
        ingredientRepository = mockk()
        ingredientService = IngredientService(ingredientRepository)
    }


    @Test
    fun `findAll should return all ingredients`() {
        every { ingredientRepository.findAll() } returns listOf(generateIngredient(), generateIngredient())
        val expectedResult = ingredientService.findAll()
        assertThat(expectedResult).hasSize(2)
    }

    private fun generateIngredient() = Instancio.of(Ingredient::class.java).create()


}