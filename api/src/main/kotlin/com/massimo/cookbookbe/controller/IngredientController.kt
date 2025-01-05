package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
import com.massimo.cookbookbe.command.ingredient.IngredientCommands
import com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand
import com.massimo.cookbookbe.domain.Ingredient
import com.massimo.cookbookbe.queries.ingredient.IngredientFilter
import com.massimo.cookbookbe.queries.ingredient.IngredientQueries
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@CrossOrigin(origins = ["http://localhost:8081"])
class IngredientController(
    private val ingredientQueries: IngredientQueries,
    private val ingredientCommands: IngredientCommands,
) {

    @GetMapping("/ingredients")
    fun findAll(
        @ModelAttribute ingredientFilter: IngredientFilter
    ): ResponseEntity<List<Ingredient>> {

        val ingredients = ingredientQueries.findAll(ingredientFilter)

        return ResponseEntity.ok(ingredients)
    }

    @GetMapping("/ingredient/{id}")
    fun findById(
        @PathVariable id: Long
    ) : ResponseEntity<Ingredient> {

        val ingredient = ingredientQueries.findById(id)
        return ResponseEntity.ok(ingredient)
    }

    @PostMapping("/ingredient")
    fun createIngredient(
        @RequestBody createIngredientCommand: CreateIngredientCommand
    ) : ResponseEntity<Long> {

        val ingredientId = ingredientCommands.handle(createIngredientCommand)
        return ResponseEntity(ingredientId, HttpStatus.CREATED)
    }

    @PutMapping("/ingredient/{id}")
    fun updateIngredient(
        @PathVariable id: Long,
        @RequestBody updateIngredientInfoCommand: UpdateIngredientInfoCommand
    ) : ResponseEntity<Long> {

        ingredientCommands.handle(id, updateIngredientInfoCommand)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/ingredient/{id}")
    fun deleteIngredient(
        @PathVariable id: Long
    ) : ResponseEntity<Unit> {

        ingredientCommands.handle(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}