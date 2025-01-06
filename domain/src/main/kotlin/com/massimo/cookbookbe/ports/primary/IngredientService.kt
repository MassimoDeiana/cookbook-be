package com.massimo.cookbookbe.ports.primary

import com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
import com.massimo.cookbookbe.domain.IngredientDomain

interface IngredientService {

    fun findAll(): List<IngredientDomain>

    fun findById(id: Long) : IngredientDomain

    fun save(createIngredientCommand: CreateIngredientCommand) : Long

    fun delete(id: Long) : Boolean

    fun update(ingredientDomain: IngredientDomain) : Boolean


}