package com.massimo.cookbookbe

import org.jetbrains.exposed.sql.SchemaUtils
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookbookBeApplication

fun main(args: Array<String>) {

    runApplication<CookbookBeApplication>(*args)

}
