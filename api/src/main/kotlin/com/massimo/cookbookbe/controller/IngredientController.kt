package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.command.ingredient.CreateIngredientCommand
import com.massimo.cookbookbe.command.ingredient.IngredientCommands
import com.massimo.cookbookbe.command.ingredient.UpdateIngredientInfoCommand
import com.massimo.cookbookbe.queries.ingredient.IngredientFilter
import com.massimo.cookbookbe.queries.ingredient.IngredientQueries
import com.massimo.cookbookbe.service.ImageAssistant
//import com.massimo.cookbookbe.service.Translator
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:8081"])
class IngredientController(
    private val ingredientQueries: IngredientQueries,
    private val ingredientCommands: IngredientCommands,
    private val imageAssistant: ImageAssistant
//    private val translator: Translator
) {

    @GetMapping("/image/{imagePath}")
    fun describeImage(@PathVariable imagePath: String) =
        imageAssistant.describeImage(imagePath)

    @GetMapping("/ingredients")
    fun findAll(@ModelAttribute ingredientFilter: IngredientFilter) =
        ingredientQueries.findAll(ingredientFilter)

    @GetMapping("/ingredient/{id}")
    fun findById(@PathVariable id: Long, @RequestParam("lang") lang: String) {
        val ingredient = ingredientQueries.findById(id)
//        ingredient.let {
//            println(translator.translate(it.name))
//            println(translator.translate(it.description))
//            println(translator.translate(it.unit.name))
//            println(translator.translate(it.category.name))
//        }
    }

    @PostMapping("/ingredient")
    fun createIngredient(@RequestBody createIngredientCommand: CreateIngredientCommand) =
        ingredientCommands.handle(createIngredientCommand)

    @PutMapping("/ingredient/{id}")
    fun updateIngredient(@PathVariable id: Long, @RequestBody updateIngredientInfoCommand: UpdateIngredientInfoCommand) =
        ingredientCommands.handle(id, updateIngredientInfoCommand)

    @DeleteMapping("/ingredient/{id}")
    fun deleteIngredient(@PathVariable id: Long) =
        ingredientCommands.handle(id)

}