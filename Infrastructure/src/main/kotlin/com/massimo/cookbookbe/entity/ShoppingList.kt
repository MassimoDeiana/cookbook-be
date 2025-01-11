package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.date

object ShoppingList : Table() {
    val id = long("id").autoIncrement()
    val date = date("date")
    val isDone = bool("is_done")

    override val primaryKey = PrimaryKey(id, name = "PK_ShoppingList_ID")
}
