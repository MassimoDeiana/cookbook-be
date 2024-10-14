package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.service.DatabaseService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DatabaseController(
    private val databaseService : DatabaseService
) {


    @GetMapping("/database/refresh")
    fun refreshDatabase() {
        databaseService.refreshDatabase()
    }

}