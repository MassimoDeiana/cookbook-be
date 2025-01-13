package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table

object Tags: Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 50)
    override val primaryKey = PrimaryKey(id, name = "PK_Tags")
}