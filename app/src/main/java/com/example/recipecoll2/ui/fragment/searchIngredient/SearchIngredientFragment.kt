package com.example.recipecoll2.ui.fragment.searchIngredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipecoll2.R
import com.example.recipecoll2.domain.model.IngredientOnlyName
import com.example.recipecoll2.ui.MainActivity
import com.example.recipecoll2.ui.fragment.IngredientAdapter
import com.example.recipecoll2.ui.fragment.callBack.OnIngredientItemSelect
import com.example.recipecoll2.ui.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_search_ingredient.*

class SearchIngredientFragment: Fragment() {

    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    lateinit var adapter: IngredientAdapter
    var ingredients= mutableListOf<IngredientOnlyName>()

    val ingredientCallBack = object : OnIngredientItemSelect {
        override fun selectIngredient(adapterPosition: Int) {
            ingredients[adapterPosition].isSelect = !ingredients[adapterPosition].isSelect
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_search_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController()
        viewModel.getAllIngredientsView()
        ingredients.addAll(viewModel.ingredientsView.sortedBy { it.name })
        adapter = IngredientAdapter(ingredients, ingredientCallBack)
        ingredientRecyclerView.adapter = adapter
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this.context)
        ingredientRecyclerView.adapter?.notifyDataSetChanged()




        btnResultSearch.setOnClickListener {
            //create list of names
            val listNameIngredientsSelect : MutableList<IngredientOnlyName> = ingredients.filter { it.isSelect }.toMutableList()

            viewModel.listOfIngredientSelected = listNameIngredientsSelect

            viewModel.searchByIngredient()


            navController.navigate(R.id.resultSearchFragment)

        }


    }


}
