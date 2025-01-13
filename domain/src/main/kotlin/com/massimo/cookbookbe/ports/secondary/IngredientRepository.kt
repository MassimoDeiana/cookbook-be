package com.massimo.cookbookbe.ports.secondary

import com.massimo.cookbookbe.domain.IngredientDomain
import com.massimo.cookbookbe.domain.IngredientFilter

interface IngredientRepository {

    fun findAll(filter: IngredientFilter) : List<IngredientDomain>

    fun findById(id: Long): IngredientDomain?

    fun save(ingredient: IngredientDomain) : Long

    fun delete(id: Long) : Boolean

    fun update(id: Long, ingredient: IngredientDomain) : Boolean

}