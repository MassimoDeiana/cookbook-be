package com.massimo.cookbookbe.ports.primary

import com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
import com.massimo.cookbookbe.domain.Ingredient

interface IngredientService {

    fun findAll(): List<Ingredient>

    fun findById(id: Long) : Ingredient

    fun save(createIngredientCommand: CreateIngredientCommand) : Long

    fun delete(id: Long) : Boolean

    fun update(ingredient: Ingredient) : Boolean


}