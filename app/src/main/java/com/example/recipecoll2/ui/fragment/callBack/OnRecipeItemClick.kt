package com.example.recipecoll2.ui.fragment.callBack

interface OnRecipeItemClick {
    fun showRecipe(adapterPosition: Int)
    fun changeFavourite(adapterPosition: Int)
}