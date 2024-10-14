package com.massimo.cookbookbe.ports.primary

import com.massimo.cookbookbe.domain.Ingredient

interface IngredientService {

    fun findAll(): List<Ingredient>

    fun findById(id: Long) : Ingredient

    fun save(ingredient: Ingredient) : Long

    fun delete(id: Long) : Boolean

    fun update(ingredient: Ingredient) : Boolean


}