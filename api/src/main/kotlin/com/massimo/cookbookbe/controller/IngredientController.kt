package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.api.IngredientApi
import com.massimo.cookbookbe.command.ingredient.IngredientCommands
import com.massimo.cookbookbe.model.Category
import com.massimo.cookbookbe.model.Ingredient
import com.massimo.cookbookbe.queries.ingredient.IngredientQueries
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["http://localhost:8081"])
class IngredientController (
    private val ingredientQueries: IngredientQueries,
    private val ingredientCommands: IngredientCommands,
) : IngredientApi {

    @GetMapping("/ingredients")
    override fun findAll(
        @RequestParam(required = false, value = "name") name: String?,
        @RequestParam(required = false, value = "category") category: Category?,
        @RequestParam(required = false, value = "order") order: String?,
        @RequestParam(required = false, value = "orderBy") orderBy: String?
    ): ResponseEntity<List<Ingredient>> {

        val ingredients = ingredientQueries.findAll(ingredientFilter)

        return ResponseEntity.ok(ingredients)
    }

    @GetMapping("/ingredient/{id}")
    override fun findById(
        @PathVariable id: Int
    ): ResponseEntity<Ingredient> {

        val ingredientDomain = ingredientQueries.findById(id)

        val ingredient = ingredientMapper.map(ingredientDomain)

        return ResponseEntity.ok(ingredient)
    }

    @PostMapping("/ingredient")
    override fun createIngredient(
        @Valid @RequestBody createIngredientCommand: com.massimo.cookbookbe.model.CreateIngredientCommand
    ): ResponseEntity<Int> {

        val ingredientId = ingredientCommands.handle(createIngredientCommand)
        return ResponseEntity(ingredientId, HttpStatus.CREATED)
    }

    @PutMapping("/ingredient/{id}")
    override fun updateIngredient(
        @PathVariable id: Int,
        @Valid @RequestBody updateIngredientInfoCommand: com.massimo.cookbookbe.model.UpdateIngredientInfoCommand
    ): ResponseEntity<Int> {

        ingredientCommands.handle(id, updateIngredientInfoCommand)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/ingredient/{id}")
    override fun deleteIngredient(
        @PathVariable id: Int
    ): ResponseEntity<Unit> {

        ingredientCommands.handle(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}