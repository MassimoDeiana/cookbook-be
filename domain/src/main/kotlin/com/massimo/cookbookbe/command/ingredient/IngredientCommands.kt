package com.massimo.cookbookbe.command.ingredient

import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import com.massimo.cookbookbe.ports.secondary.IngredientRepository
import org.springframework.stereotype.Service

@Service
class IngredientCommands (
    private val ingredientRepository: IngredientRepository
) {

    fun handle(createIngredientCommand: CreateIngredientCommand) =
        ingredientRepository.save(createIngredientCommand)

    fun handle(id: Long, updateIngredientInfoCommand: UpdateIngredientInfoCommand) =
        ingredientRepository.update(id, updateIngredientInfoCommand)

    fun handle(id: Long) {
        val isDeleted = ingredientRepository.delete(id)
        if(!isDeleted) throw IngredientNotFoundException(id)
    }

}