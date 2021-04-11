package com.example.recipecoll2.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipecoll2.R
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.ui.fragment.callBack.OnRecipeItemClick
import com.example.recipecoll2.ui.model.RecipeView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*

class RecipeAdapter(
    val recipes: MutableList<RecipeView>,
    val recipeCallback: OnRecipeItemClick
) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.nameRecipe)
        val ingredients = itemView.findViewById<TextView>(R.id.ingredientsRecipe)
        val icon = itemView.findViewById<ImageView>(R.id.imageRecipe)
        val iconFavorite = itemView.findViewById<ImageView>(R.id.imageRecipeAddFavorite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recipe_item, parent, false)
        val holder = RecipeViewHolder(itemView)

        holder.itemView.setOnClickListener {
            recipeCallback.showRecipe(holder.adapterPosition)
        }

        holder.itemView.imageRecipeAddFavorite.setOnClickListener {
            recipeCallback.changeFavourite(holder.adapterPosition)
        }

        return holder
    }

    override fun getItemCount(): Int {
        return recipes.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.name.text = recipes[position].title

        var body = ""
        for (i in 0 until recipes[position].extendedIngredients.size) {
            if (i != 0) {
                body += ", ${recipes[position].extendedIngredients[i].nameClean}"
            } else {
                body += "Ingredients: ${recipes[position].extendedIngredients[i].nameClean}"
            }
        }
        holder.ingredients.text = body

        Picasso.get().load(recipes[position].image).into(holder.icon)

        if (recipes[position].isFavorite == 0) {
            holder.iconFavorite.setImageResource(R.drawable.ic_favorite_border_24)
        } else {
            holder.iconFavorite.setImageResource(R.drawable.ic_favorite_24)
        }
    }
}