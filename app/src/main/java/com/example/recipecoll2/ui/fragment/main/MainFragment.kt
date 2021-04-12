package com.example.recipecoll2.ui.fragment.main


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.RecipeAdapter
import com.example.recipecoll2.ui.fragment.callBack.OnRecipeItemClick
import com.example.recipecoll2.ui.model.RecipeView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment() {
    lateinit var navController: NavController
    val viewModel: MainViewModel by viewModels()
    lateinit var adapter: RecipeAdapter
    var recipes = mutableListOf<RecipeView>()

    val recipeCallback = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {

        }

        override fun changeFavourite(adapterPosition: Int) {
            if (recipes[adapterPosition].isFavorite == 0) {
                viewModel.updateInFavorites(recipes[adapterPosition].id)
            } else {
                viewModel.updateOutFavorites(recipes[adapterPosition].id)
            }
            mainRecyclerView.adapter!!.notifyItemChanged(adapterPosition)

        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()




        viewModel.recipeMutableLiveData.value?.let {
            recipes = viewModel.recipeMutableLiveData.value!!
        }

        adapter = RecipeAdapter(recipes, recipeCallback)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.recipeMutableLiveData.observe(viewLifecycleOwner)
        {
            recipes.clear()
            recipes.addAll(it)
            mainRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
}

