package com.massimo.cookbookbe.service

import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.ports.primary.IngredientService
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import org.springframework.stereotype.Service

@Service
class IngredientService(
    private val ingredientRepository: IngredientRepository
) : IngredientService
{

    override fun findAll() = ingredientRepository.findAll()

    override fun findById(id: Long): Ingredient {
        TODO("Not yet implemented")
    }

    override fun save(ingredient: Ingredient): Long {
        TODO("Not yet implemented")
    }

    override fun delete(id: Long): Boolean {
        TODO("Not yet implemented")
    }

    override fun update(ingredient: Ingredient): Boolean {
        TODO("Not yet implemented")
    }


}