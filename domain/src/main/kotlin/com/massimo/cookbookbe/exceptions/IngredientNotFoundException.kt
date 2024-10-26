package com.massimo.cookbookbe.exceptions

class IngredientNotFoundException(id: Long) : Exception("Ingredient not found for id : $id")