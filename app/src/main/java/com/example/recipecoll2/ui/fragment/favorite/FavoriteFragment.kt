package com.example.recipecoll2.ui.fragment.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.fragment.RecipeAdapter
import com.example.recipecoll2.ui.fragment.callBack.OnRecipeItemClick
import com.example.recipecoll2.ui.fragment.information.InformationViewModel
import com.example.recipecoll2.ui.model.RecipeView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_favorite.*

@AndroidEntryPoint
class FavoriteFragment : Fragment() {
    var navController: NavController? = null
    val viewModel: FavoriteViewModel by viewModels()
    val informationViewModel: InformationViewModel by viewModels()
    var favoriteRecipesList = mutableListOf<RecipeView>()

    private val recipeCallBack = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {
            informationViewModel.informationMutableLiveData = favoriteRecipesList[adapterPosition]
            navController?.navigate(R.id.informationFragment)
        }

        override fun changeFavourite(adapterPosition: Int) {
            viewModel.updateOutFavorites(favoriteRecipesList[adapterPosition].id)
            favoriteRecyclerView.adapter?.notifyDataSetChanged()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        viewModel.getFavorites()

        viewModel.favoriteMutableLiveData.value?.let {
            favoriteRecipesList = viewModel.favoriteMutableLiveData.value!!
        }

        val adapter = RecipeAdapter(favoriteRecipesList, recipeCallBack)
        favoriteRecyclerView.adapter = adapter
        favoriteRecyclerView.layoutManager = LinearLayoutManager(requireContext())


        viewModel.favoriteMutableLiveData.observe(viewLifecycleOwner, Observer {
            favoriteRecipesList.clear()
            favoriteRecipesList.addAll(it)
            favoriteRecyclerView.adapter?.notifyDataSetChanged()
        })
    }
}