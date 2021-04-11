package com.example.recipecoll2.domain.model

data class DomainIngredient(
    var id: Int,
    var image:String,
    var nameClean: String,
    var amount: String,
    var unit: String,
    var recipe_id: Int,
    var key : Int=0,
    var isSelect : Int =0
)