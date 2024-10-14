package com.massimo.cookbookbe.service

import com.massimo.cookbookbe.ports.secondary.DatabaseUtils
import org.springframework.stereotype.Service

@Service
class DatabaseService(
    private val databaseUtils: DatabaseUtils
) {

    fun refreshDatabase() {
        databaseUtils.refreshDatabase()
    }

}