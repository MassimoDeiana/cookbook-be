package com.massimo.cookbookbe.ports.secondary

import com.massimo.cookbookbe.domain.Ingredient

interface IngredientRepository {

    fun findAll() : List<Ingredient>

    fun save(ingredient: Ingredient) : Int

    fun findById(ingredientId: Int): Ingredient

    fun delete(ingredientId: Int) : Boolean

    fun update(ingredient: Ingredient) : Boolean
}