package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.api.IngredientApi
import com.massimo.cookbookbe.domain.CategoryDomain
import com.massimo.cookbookbe.mapper.IngredientMapper
import com.massimo.cookbookbe.model.Category
import com.massimo.cookbookbe.model.Ingredient
import com.massimo.cookbookbe.ports.primary.IngredientService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@CrossOrigin(origins = ["http://localhost:8081"])
class IngredientController (
    private val ingredientService: IngredientService,
    private val ingredientMapper: IngredientMapper
) : IngredientApi {

    @GetMapping("/ingredients")
    override fun findAll(
        @RequestParam(required = false, value = "name") name: String?,
        @RequestParam(required = false, value = "category") category: Category?,
        @RequestParam(required = false, value = "order") order: String?,
        @RequestParam(required = false, value = "orderBy") orderBy: String?
    ): ResponseEntity<List<Ingredient>> {

        val ingredientFilter = com.massimo.cookbookbe.domain.IngredientFilter(
            name = name,
            category = CategoryDomain.valueOf(category?.name ?: ""),
            order = order,
            orderBy = orderBy
        )

        val ingredients = ingredientService.findAll(ingredientFilter)
        val response = ingredients.map { ingredientMapper.map(it) }
        return ResponseEntity.ok(response)
    }

    @GetMapping("/ingredient/{id}")
    override fun findById(
        @PathVariable id: Int
    ): ResponseEntity<Ingredient> {

        val ingredientDomain = ingredientService.findById(id)
        val ingredient = ingredientMapper.map(ingredientDomain)
        return ResponseEntity.ok(ingredient)
    }

    @PostMapping("/ingredient")
    override fun createIngredient(
        @Valid @RequestBody createIngredientCommand: com.massimo.cookbookbe.model.CreateIngredientCommand
    ): ResponseEntity<Int> {

        val ingredientToCreate = ingredientMapper.map(createIngredientCommand)
        val ingredientId = ingredientService.create(ingredientToCreate)
        return ResponseEntity(ingredientId, HttpStatus.CREATED)
    }

    @PutMapping("/ingredient/{id}")
    override fun updateIngredient(
        @PathVariable id: Int,
        @Valid @RequestBody updateIngredientInfoCommand: com.massimo.cookbookbe.model.UpdateIngredientInfoCommand
    ): ResponseEntity<Int> {

        val ingredientToUpdate = ingredientMapper.map(updateIngredientInfoCommand)
        ingredientService.update(id, ingredientToUpdate)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/ingredient/{id}")
    override fun deleteIngredient(
        @PathVariable id: Int
    ): ResponseEntity<Unit> {

        ingredientService.delete(id)
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }

}