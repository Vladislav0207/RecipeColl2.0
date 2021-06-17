package com.example.recipecoll2.ui.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.fragment.callBack.OnIngredientItemSelect
import com.example.recipecoll2.ui.model.IngredientOnlyNameView
import kotlinx.android.synthetic.main.ingredient_item.view.*

class IngredientAdapter(
    private val ingredients: MutableList<IngredientOnlyNameView>,
    private val ingredientCallBack: OnIngredientItemSelect
) :
    RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.nameSearchIngredient)
        val imageSelect: ImageView = itemView.findViewById(R.id.imageSelectSearchIngredient)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.ingredient_item, parent, false)
        val holder = IngredientViewHolder(itemView)


        holder.itemView.imageSelectSearchIngredient.setOnClickListener {
            ingredientCallBack.selectIngredient(holder.adapterPosition)
            notifyItemChanged(holder.adapterPosition)
        }

        return holder
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.name.text = ingredients[position].name
        if (ingredients[position].isSelect) {
            holder.imageSelect.setImageResource(R.drawable.ic_baseline_check_box_24)
        } else {
            holder.imageSelect.setImageResource(R.drawable.ic_baseline_check_box_outline_blank_24)
        }
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }
}