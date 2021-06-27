package com.freeze.ui.ui.fragment.callBack

interface OnRecipeItemClick {
    fun showRecipe(adapterPosition: Int)
    fun changeFavourite(adapterPosition: Int)
}