package com.freeze.ui.ui.fragment.resultSearch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import com.freeze.ui.ui.fragment.searchIngredient.ShareResultViewModel
import com.freeze.ui.ui.model.RecipeView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_result_search.*

@AndroidEntryPoint
class ResultSearchFragment : Fragment() {
    lateinit var navController: NavController
    val viewModel: ResultSearchViewModel by viewModels()
    val informationViewModel: InformationViewModel by activityViewModels()
    val shareResultViewModel: ShareResultViewModel by activityViewModels()
    var resultList = mutableListOf<RecipeView>()

    private val recipeCallback = object : OnRecipeItemClick {
        override fun showRecipe(adapterPosition: Int) {
            informationViewModel.informationMutableLiveData = resultList[adapterPosition]
            navController.navigate(R.id.informationFragment)
        }

        override fun changeFavourite(adapterPosition: Int) {
            if (resultList[adapterPosition].isFavorite == 0) {
                viewModel.updateInFavorites(resultList[adapterPosition].id)
            } else {
                viewModel.updateOutFavorites(resultList[adapterPosition].id)
            }
            shareResultViewModel.changedIngredients.value?.let {
                viewModel.getResult(shareResultViewModel.changedIngredients.value!!)
            }
            resultSearchRecyclerView.adapter?.notifyDataSetChanged()

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_result_search, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        shareResultViewModel.changedIngredients.value?.let {
            viewModel.getResult(shareResultViewModel.changedIngredients.value!!)
            viewModel.recipeResultMutableLiveData.value?.let {
                resultList = viewModel.recipeResultMutableLiveData.value!!
            }
        }

        val adapter = RecipeAdapter(resultList, recipeCallback)
        resultSearchRecyclerView.adapter = adapter
        resultSearchRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel.recipeResultMutableLiveData.observe(viewLifecycleOwner) {
            resultList.clear()
            resultList.addAll(it)
            resultSearchRecyclerView.adapter?.notifyDataSetChanged()
        }

    }
}