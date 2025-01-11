package com.massimo.cookbookbe.entity

import org.jetbrains.exposed.sql.Table

object ShoppingListItem: Table() {
    val idShoppingList = long("id_shopping_list").references(ShoppingList.id)
    val idIngredient = long("id_ingredient").references(Ingredients.id)
    val quantity = integer("quantity")
    val isBought = bool("is_bought")
}