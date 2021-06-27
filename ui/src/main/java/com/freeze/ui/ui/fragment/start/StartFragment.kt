package com.freeze.ui.ui.fragment.start


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freeze.ui.R
import com.freeze.ui.ui.fragment.RecipeAdapter
import com.freeze.ui.ui.fragment.callBack.OnRecipeItemClick
import com.freeze.ui.ui.fragment.information.InformationViewModel
import com.freeze.ui.ui.model.RecipeView

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_start.*
import java.util.*

@AndroidEntryPoint
class StartFragment : Fragment() {

    lateinit var navController: NavController
    val viewModel: StartViewModel by viewModels()
    val informationViewModel: InformationViewModel by activityViewModels()
    private lateinit var adapter: RecipeAdapter
    var recipes = mutableListOf<RecipeView>()

    private val recipeCallback = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {
            informationViewModel.informationMutableLiveData = recipes[adapterPosition]
            navController.navigate(R.id.informationFragment)
    }

        override fun changeFavourite(adapterPosition: Int) {
            if (recipes[adapterPosition].isFavorite == 0) {
                viewModel.updateInFavorites(recipes[adapterPosition].id)
            } else {
                viewModel.updateOutFavorites(recipes[adapterPosition].id)
            }
            mainRecyclerView.adapter?.notifyItemChanged(adapterPosition)

        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel.getData()

        viewModel.recipeMutableLiveData.value?.let {
            recipes = viewModel.recipeMutableLiveData.value!!
        }
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()



        adapter = RecipeAdapter(recipes, recipeCallback)
        mainRecyclerView.adapter = adapter
        mainRecyclerView.layoutManager = LinearLayoutManager(context)

        viewModel.recipeMutableLiveData.observe(viewLifecycleOwner)
        {
            recipes.clear()
            recipes.addAll(it)
            mainRecyclerView.adapter?.notifyDataSetChanged()
        }

        startSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (recipes.none {
                        it.title.toLowerCase(Locale.ROOT).contains(query!!)
                    })
                    {
                        Toast.makeText(activity, getString(R.string.recipeNotFound), Toast.LENGTH_SHORT).show()
                    } else
                {
                    adapter.filter.filter(query)
                }
                return false
            }
        })
    }
}



