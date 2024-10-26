package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.command.ingredient.IngredientCommands
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.exception.ExceptionHandler
import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import com.massimo.cookbookbe.queries.ingredient.IngredientFilter
import com.massimo.cookbookbe.queries.ingredient.IngredientQueries
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.instancio.Instancio
import org.instancio.Select.field
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import kotlin.test.Test

@SpringBootTest
class IngredientControllerTest {

    @MockkBean
    private lateinit var ingredientQueries: IngredientQueries

    @MockkBean
    private lateinit var ingredientCommands: IngredientCommands

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup(){
        mockMvc = MockMvcBuilders
            .standaloneSetup(IngredientController(ingredientQueries, ingredientCommands))
            .setControllerAdvice(ExceptionHandler())
            .build()
    }

    @Test
    fun `findAll should return all ingredients`() {
        val firstIngredient = ingredient()
        val secondIngredient = ingredient()
        every { ingredientQueries.findAll( IngredientFilter() )} returns listOf(firstIngredient, secondIngredient)

        mockMvc.perform(get("/ingredients"))
            .andExpectAll(
                status().isOk,
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$").isArray,
                jsonPath("$[0].name").value(firstIngredient.name),
                jsonPath("$[1].name").value(secondIngredient.name)
            )
    }

    @Test
    fun `findById should return an ingredient`() {
        val ingredient = ingredient()
        every { ingredientQueries.findById(any()) } returns ingredient

        mockMvc.perform(get("/ingredient/1"))
            .andExpectAll(
                status().isOk,
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.name").value(ingredient.name)
            )
    }

    @Test
    fun `findById should return status 404 when ingredient is not found`() {
        val errorMessage = "Ingredient not found for id : 1"
        every { ingredientQueries.findById(any()) } throws IngredientNotFoundException(1)

        mockMvc.perform(get("/ingredient/1"))
            .andExpectAll(
                status().isNotFound,
                content().contentType(MediaType.APPLICATION_JSON),
                jsonPath("$.message").value(errorMessage),
                jsonPath("$.statusCode").value(404)
            )
    }


    private fun ingredient() : Ingredient {
        return Instancio.of(Ingredient::class.java)
            .set(field("id"), 1)
            .create()
    }


}