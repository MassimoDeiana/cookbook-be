package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table

object Ingredients : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 50).uniqueIndex()
    val description = varchar("description", 255)
    val unit = enumerationByName<Units>("unit", 50)
    val category = enumerationByName<Categories>("category", 50)
    val quantityInStock = integer("quantity_in_stock").default(0)

    override val primaryKey = PrimaryKey(id, name = "PK_Ingredient_ID")

}