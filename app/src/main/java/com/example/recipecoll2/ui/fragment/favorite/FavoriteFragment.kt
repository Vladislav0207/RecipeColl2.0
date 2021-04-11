package com.example.recipecoll2.ui.fragment.favorite

import android.os.Bundle
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
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_main.*

class FavoriteFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: FavoriteViewModel
    var favoriteRecipesList = mutableListOf<RecipeView>()

    val recipeCallBack = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {
            viewModel.showRecipe = favoriteRecipesList[adapterPosition]
            navController.navigate(R.id.informationFragment)
        }

        override fun changeFavourite(adapterPosition: Int) {
            viewModel.updateOutFavorites(favoriteRecipesList[adapterPosition].id)
            favoriteRecipesList.remove(favoriteRecipesList[adapterPosition])
            favoriteRecyclerView.adapter!!.notifyDataSetChanged()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(activity as MainActivity).get(FavoriteViewModel::class.java)


        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        favoriteRecipesList = viewModel.favoriteMutableLiveData.value!!


        val adapter = RecipeAdapter(favoriteRecipesList, recipeCallBack)
        favoriteRecyclerView.adapter = adapter
        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        viewModel.favoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
            favoriteRecipesList.clear()
            favoriteRecipesList.addAll(it)
            if (favoriteRecyclerView != null) {
                favoriteRecyclerView.adapter?.notifyDataSetChanged()
            }
        })
    }
}