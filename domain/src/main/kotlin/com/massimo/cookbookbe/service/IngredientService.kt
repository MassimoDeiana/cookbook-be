package com.massimo.cookbookbe.service

import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import com.massimo.cookbookbe.ports.primary.IngredientService
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import org.springframework.stereotype.Service

@Service
class IngredientService(
    private val ingredientRepository: IngredientRepository
) : IngredientService
{

    override fun findAll() = ingredientRepository.findAll()

    override fun findById(id: Long) = ingredientRepository.findById(id)
        ?: throw IngredientNotFoundException("Ingredient not found for id : $id")


    override fun save(ingredient: Ingredient) = ingredientRepository.save(ingredient)

    override fun delete(id: Long) = ingredientRepository.delete(id)

    override fun update(ingredient: Ingredient) = ingredientRepository.update(ingredient)


}