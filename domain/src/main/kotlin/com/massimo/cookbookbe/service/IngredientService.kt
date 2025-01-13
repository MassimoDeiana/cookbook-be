package com.massimo.cookbookbe.service

import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import com.massimo.cookbookbe.domain.IngredientFilter
import com.massimo.cookbookbe.ports.primary.IngredientService
import org.springframework.stereotype.Service

@Service
class IngredientService(
    private val ingredientRepository: IngredientRepository
) : IngredientService {

    override fun findAll(ingredientFilter : IngredientFilter) =
        ingredientRepository.findAll(ingredientFilter)

    override fun findById(id: Long) =
        ingredientRepository.findById(id)
            ?: throw IngredientNotFoundException(id)

    override fun create(ingredient: IngredientDomain) =
        ingredientRepository.save(ingredient)

    override fun update(id: Long, ingredient: IngredientDomain) =
        ingredientRepository.update(id, ingredient)

    override fun delete(id: Long) {
        val isDeleted = ingredientRepository.delete(id)
        if(!isDeleted) throw IngredientNotFoundException(id)
    }
}