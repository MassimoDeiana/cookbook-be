package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.datetime

object Recipe: Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 50).uniqueIndex()
    val description = varchar("description", 255)
    val preparationTime = integer("preparation_time")
    val cookingTime = integer("cooking_time")
    val servings = integer("servings")
    val difficulty = enumerationByName<Difficulty>("difficulty", 50)
    val category = enumerationByName<Categories>("category", 50)
    val instructions = text("instructions")
    val imageUrl = varchar("image_url", 255)
    val planning = datetime("planning")

    override val primaryKey = PrimaryKey(id, name = "PK_Recipe_ID")
}