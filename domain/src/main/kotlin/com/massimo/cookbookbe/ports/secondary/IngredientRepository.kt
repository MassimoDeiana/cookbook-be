package com.massimo.cookbookbe.ports.secondary

import com.massimo.cookbookbe.domain.Ingredient

interface IngredientRepository {

    fun findAll() : List<Ingredient>

    fun findById(ingredientId: Long): Ingredient?

    fun save(ingredient: Ingredient) : Long

    fun delete(ingredientId: Long) : Boolean

    fun update(ingredient: Ingredient) : Boolean
}