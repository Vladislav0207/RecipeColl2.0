package com.freeze.ui.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.freeze.ui.R
import com.freeze.ui.ui.fragment.callBack.OnRecipeItemClick
import com.freeze.ui.ui.model.RecipeView

import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.recipe_item.view.*
import java.util.*

class RecipeAdapter(
    private val recipes: MutableList<RecipeView>,
    private val recipeCallback: OnRecipeItemClick
) :
    RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>(), Filterable {

    var recipesAdapter = recipes

    class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameRecipe)
        val ingredients: TextView = itemView.findViewById(R.id.ingredientsRecipe)
        val icon: ImageView = itemView.findViewById(R.id.imageRecipe)
        val iconFavorite: ImageView = itemView.findViewById(R.id.imageRecipeAddFavorite)
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
        return recipesAdapter.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {

        holder.name.text = recipesAdapter[position].title

        var body = ""
        for (i in recipesAdapter[position].extendedIngredients.indices) {
            body += if (i != 0) {
                ", ${recipesAdapter[position].extendedIngredients[i].nameClean}"
            } else {
                "Ingredients: ${recipesAdapter[position].extendedIngredients[i].nameClean}"
            }
        }
        holder.ingredients.text = body

        Picasso.get().load(recipesAdapter[position].image).into(holder.icon)

        if (recipesAdapter[position].isFavorite == 0) {
            holder.iconFavorite.setImageResource(R.drawable.ic_favorite_border_24)
        } else {
            holder.iconFavorite.setImageResource(R.drawable.ic_favorite_24)
        }
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint.toString().toLowerCase(Locale.ROOT)

                val filterResults =FilterResults()
                filterResults.values = if (queryString.isEmpty()){
                    recipes
                }
                else {
                    recipes.filter {
                        it.title.toLowerCase(Locale.ROOT).contains(queryString)
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                recipesAdapter = results?.values as MutableList<RecipeView>
                notifyDataSetChanged()
            }

        }
    }

}