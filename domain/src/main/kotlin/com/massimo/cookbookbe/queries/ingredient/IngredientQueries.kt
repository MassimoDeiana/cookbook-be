package com.massimo.cookbookbe.queries.ingredient

import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import org.springframework.stereotype.Service

@Service
class IngredientQueries(
    private val ingredientRepository: IngredientRepository
){

    fun findAll(ingredientFilter : IngredientFilter) = ingredientRepository.findAll(ingredientFilter)

    fun findById(id: Long) = ingredientRepository.findById(id)
        ?: throw IngredientNotFoundException("Ingredient not found for id : $id")


}