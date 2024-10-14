package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.exception.ExceptionHandler
import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import com.massimo.cookbookbe.service.IngredientService
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
    private lateinit var ingredientService: IngredientService

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    fun setup(){
        mockMvc = MockMvcBuilders
            .standaloneSetup(IngredientController(ingredientService))
            .setControllerAdvice(ExceptionHandler())
            .build()
    }

    @Test
    fun `findAll should return all ingredients`() {
        val firstIngredient = ingredient()
        val secondIngredient = ingredient()
        every { ingredientService.findAll() } returns listOf(firstIngredient, secondIngredient)

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
    fun `findById should return an ingredient and status 200`() {
        val ingredient = ingredient()
        every { ingredientService.findById(any()) } returns ingredient

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
        every { ingredientService.findById(any()) } throws IngredientNotFoundException(errorMessage)

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