package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table

object Ingredients : Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 50)
    val description = varchar("description", 255)

    override val primaryKey = PrimaryKey(id, name = "PK_Ingredient_ID")
}