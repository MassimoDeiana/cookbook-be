package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.api.IngredientApi
import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.mapper.IngredientMapper
import com.massimo.cookbookbe.model.Category
import com.massimo.cookbookbe.model.CreateIngredientCommand
import com.massimo.cookbookbe.model.Ingredient
import com.massimo.cookbookbe.model.UpdateIngredientInfoCommand
import com.massimo.cookbookbe.ports.primary.IngredientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["http://localhost:8081"])
class IngredientController (
    private val service: IngredientService,
    private val mapper: IngredientMapper
) : IngredientApi {

    @GetMapping("/ingredient")
    override fun findAllIngredients(
        @RequestParam(required = false, value = "name") name: String?,
        @RequestParam(required = false, value = "category") category: Category?,
        @RequestParam(required = false, value = "order") order: String?,
        @RequestParam(required = false, value = "orderBy") orderBy: String?
    ): ResponseEntity<List<Ingredient>> {

        val ingredientFilter = com.massimo.cookbookbe.domain.IngredientFilter(
            name = name,
            category = category?.let { CategoryDomain.valueOf(it.name) },
            order = order,
            orderBy = orderBy
        )

        val ingredients = service.findAll(ingredientFilter)
        val response = ingredients.map { mapper.map(it) }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/ingredient/{id}")
    override fun findIngredientById(
        @PathVariable id: Long
    ): ResponseEntity<Ingredient> {

        val ingredientDomain = service.findById(id)
        val ingredient = mapper.map(ingredientDomain)
        return ResponseEntity.ok(ingredient)
    }

    @PostMapping("/ingredient")
    override fun createIngredient(
        @Valid @RequestBody createIngredientCommand: CreateIngredientCommand
    ): ResponseEntity<Long> {

        val ingredientToCreate = mapper.map(createIngredientCommand)
        val ingredientId = service.create(ingredientToCreate)
        return ResponseEntity(ingredientId, HttpStatus.CREATED)
    }

    @PutMapping("/ingredient/{id}")
    override fun updateIngredient(
        @PathVariable id: Long,
        @Valid @RequestBody updateIngredientInfoCommand: UpdateIngredientInfoCommand
    ): ResponseEntity<Long> {

        val ingredientToUpdate = mapper.map(updateIngredientInfoCommand)
        service.update(id, ingredientToUpdate)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/ingredient/{id}")
    override fun deleteIngredient(
        @PathVariable id: Long
    ): ResponseEntity<Unit> {

        service.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}