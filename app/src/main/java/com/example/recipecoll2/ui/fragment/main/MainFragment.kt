package com.example.recipecoll2.ui.fragment.main

import android.content.Intent
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
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.ui.MainActivity
import com.example.recipecoll2.ui.RecipeAdapter
import com.example.recipecoll2.ui.fragment.callBack.OnRecipeItemClick
import com.example.recipecoll2.ui.fragment.information.InformationFragment
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: MainViewModel
    lateinit var adapter: RecipeAdapter
    var recipes = mutableListOf<RecipeView>()

    val recipeCallback = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {
            val intent = Intent(context, InformationFragment::class.java)
            intent.putExtra("recipeID", recipes[adapterPosition].id)
            startActivity(intent)
        }

        override fun changeFavourite(adapterPosition: Int) {
            if (recipes[adapterPosition].isFavorite == 0) {
                viewModel.updateInFavorites(adapterPosition)
            } else {
                viewModel.updateOutFavorites(recipes[adapterPosition].id)
                viewModel.recipeMutableLiveData.value!![adapterPosition].isFavorite = 0
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
        viewModel = ViewModelProvider(activity as MainActivity).get(MainViewModel::class.java)



        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()




        viewModel.getData()
        recipes = viewModel.recipeMutableLiveData.value!!


        adapter = RecipeAdapter(recipes, recipeCallback)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.recipeMutableLiveData.observe(viewLifecycleOwner, Observer {
            recipes.clear()
            recipes.addAll(it)
            if(mainRecyclerView != null) {
                mainRecyclerView.adapter?.notifyDataSetChanged()
            }
        })
    }
}

