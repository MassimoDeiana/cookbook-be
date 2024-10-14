package com.massimo.cookbookbe

import com.massimo.cookbookbe.entity.Ingredients
import com.massimo.cookbookbe.ports.secondary.DatabaseUtils
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Component

@Component
class DatabaseUtils : DatabaseUtils {

    override fun refreshDatabase() {
        transaction {
            SchemaUtils.drop(Ingredients)
            SchemaUtils.create(Ingredients)
        }
        println("Database refreshed")
    }

}