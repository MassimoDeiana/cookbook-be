package com.massimo.cookbookbe.ports.primary

import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.domain.IngredientFilter

interface IngredientService {

    fun findAll(ingredientFilter : IngredientFilter) : List<IngredientDomain>

    fun findById(id: Long) : IngredientDomain

    fun create(ingredient: IngredientDomain) : Long

    fun update(id: Long, ingredient: IngredientDomain) : Boolean

    fun delete(id: Long)


}