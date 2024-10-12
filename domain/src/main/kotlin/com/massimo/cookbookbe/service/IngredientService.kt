package com.massimo.cookbookbe.service

import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import org.springframework.stereotype.Service

@Service
class IngredientService(
    private val ingredientRepository: IngredientRepository
) {

    fun findAll() {
        ingredientRepository.findAll()
    }

}