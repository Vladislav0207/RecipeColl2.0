package com.example.recipecoll2.ui.fragment.resultSearch

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
import com.example.recipecoll2.database.model.Recipe
import com.example.recipecoll2.ui.MainActivity
import com.example.recipecoll2.ui.RecipeAdapter
import com.example.recipecoll2.ui.fragment.callBack.OnRecipeItemClick
import com.example.recipecoll2.ui.model.RecipeView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_result_search.*

class ResultSearchFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: ResultSearchViewModel
    var resultList = mutableListOf<RecipeView>()

    val recipeCallback = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {
            navController.navigate(R.id.informationFragment)
        }

        override fun changeFavourite(adapterPosition: Int) {
            if (resultList[adapterPosition].isFavorite == 0) {
                viewModel.updateInFavorites(resultList[adapterPosition].id)
            } else {
                viewModel.updateOutFavorites(resultList[adapterPosition].id)
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
        return inflater.inflate(R.layout.fragment_result_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        resultList.clear()

        navController = findNavController()


        val adapter = RecipeAdapter(resultList, recipeCallback)
        resultSearchRecyclerView.adapter = adapter
        resultSearchRecyclerView.layoutManager = LinearLayoutManager(this.context)
        resultSearchRecyclerView.adapter?.notifyDataSetChanged()


    }
}