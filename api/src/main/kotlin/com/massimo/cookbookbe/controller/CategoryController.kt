package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.api.CategoriesApi
import com.massimo.cookbookbe.model.Category
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:8081"])
class CategoryController : CategoriesApi {


    @GetMapping("/categories")
    override fun findAllCategories(): ResponseEntity<List<Category>> {
        val categories = Category.entries

        return ResponseEntity.ok(categories)
    }

}