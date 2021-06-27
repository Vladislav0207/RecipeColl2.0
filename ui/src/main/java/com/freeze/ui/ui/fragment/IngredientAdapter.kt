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
import com.freeze.ui.ui.fragment.callBack.OnIngredientItemSelect
import com.freeze.ui.ui.model.IngredientOnlyNameView

import kotlinx.android.synthetic.main.ingredient_item.view.*
import java.util.*

class IngredientAdapter(
    private val ingredients: MutableList<IngredientOnlyNameView>,
    private val ingredientCallBack: OnIngredientItemSelect
) :
    RecyclerView.Adapter<com.freeze.ui.ui.fragment.IngredientAdapter.IngredientViewHolder>(),Filterable {
    var ingredientsForAdapter = ingredients

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameSearchIngredient)
        val imageSelect: ImageView = itemView.findViewById(R.id.imageSelectSearchIngredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item, parent, false)
        val holder =
           IngredientViewHolder(
                itemView
            )


        holder.itemView.imageSelectSearchIngredient.setOnClickListener {
            ingredientCallBack.selectIngredient(holder.adapterPosition)
            notifyItemChanged(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder:IngredientViewHolder, position: Int) {
        holder.name.text = ingredientsForAdapter[position].name
        if (ingredientsForAdapter[position].isSelect) {
            holder.imageSelect.setImageResource(R.drawable.ic_baseline_check_box_24)
        } else {
            holder.imageSelect.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)
        }
    }

    override fun getItemCount(): Int {
        return ingredientsForAdapter.size
    }

    override fun getFilter(): Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val queryString = constraint.toString().toLowerCase(Locale.ROOT)

                val filterResults = FilterResults()
                filterResults.values = if (queryString.isEmpty()){
                    ingredients
                }
                else {
                    ingredients.filter {
                        it.name.toLowerCase(Locale.ROOT).contains(queryString)
                    }
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                ingredientsForAdapter = results?.values as MutableList<IngredientOnlyNameView>
                notifyDataSetChanged()
            }

        }
    }
}