package com.massimo.cookbookbe.exception

import com.massimo.cookbookbe.exceptions.IngredientNotFoundException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.http.HttpStatus
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(IngredientNotFoundException::class)
    fun handleIngredientNotFoundException(ex: IngredientNotFoundException, request: WebRequest) : ResponseEntity<ErrorDetails> {
        val status = HttpStatus.NOT_FOUND
        return ResponseEntity(
            ErrorDetails(
                status = status,
                statusCode = status.value(),
                message = ex.message!!,
                path = request.getDescription(false)
            ),
            status
        )
    }
}

data class ErrorDetails(
    val status: HttpStatus,
    val statusCode: Int,
    val message: String,
    val path: String
)
