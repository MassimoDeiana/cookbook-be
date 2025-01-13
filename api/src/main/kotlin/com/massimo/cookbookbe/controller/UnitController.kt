package com.massimo.cookbookbe.controller

import com.massimo.cookbookbe.api.UnitsApi
import com.massimo.cookbookbe.model.Unit
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(origins = ["http://localhost:8081"])
class UnitController : UnitsApi {

    @GetMapping("/units")
    override fun findAllUnits(): ResponseEntity<List<Unit>> {
        val units = Unit.entries

        return ResponseEntity.ok(units)
    }

}