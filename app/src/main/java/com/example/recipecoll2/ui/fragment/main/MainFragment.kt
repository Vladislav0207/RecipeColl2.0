package com.example.recipecoll2.ui.fragment.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipecoll2.R
import com.example.recipecoll2.network.Recipe
import com.example.recipecoll2.ui.MainActivity
import com.example.recipecoll2.ui.RecipeAdapter
import com.example.recipecoll2.ui.fragment.callBack.OnRecipeItemClick
import com.example.recipecoll2.ui.viewModel.RecipeViewModel
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    lateinit var adapter: RecipeAdapter
    var recipes = mutableListOf<Recipe>()

    val recipeCallback = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {
            viewModel.showRecipe = recipes[adapterPosition]
            navController.navigate(R.id.informationFragment)
        }

        override fun changeFavourite(adapterPosition: Int) {
            if (recipes[adapterPosition].isFavorite == 0) {
                viewModel.updateInFavorites(adapterPosition)
            } else {
                viewModel.updateOutFavorites(recipes[adapterPosition].id)
                viewModel.recipeLive.value!![adapterPosition].isFavorite = 0
            }
            mainRecyclerView.adapter!!.notifyItemChanged(adapterPosition)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)



        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()



        viewModel.getAllIngredientsView()
        viewModel.getFavorites()
        viewModel.getData()
        recipes = viewModel.recipeLive.value!!


        adapter = RecipeAdapter(recipes, recipeCallback)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.recipeLive.observe(activity as MainActivity, Observer {
            recipes.clear()
            recipes.addAll(it)
            Log.d("!!!R",it.toString())

            if(mainRecyclerView != null) {
                mainRecyclerView.adapter?.notifyDataSetChanged()
            }
        })
    }
}

