package com.massimo.cookbookbe.ports.secondary

import com.massimo.cookbookbe.domain.Ingredient

interface IngredientRepository {

    fun findAll() : List<Ingredient>
}