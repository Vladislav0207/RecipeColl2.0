package com.example.recipecoll2.ui.fragment.searchIngredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.fragment.IngredientAdapter
import com.example.recipecoll2.ui.fragment.callBack.OnIngredientItemSelect
import com.example.recipecoll2.ui.model.IngredientOnlyNameView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_ingredient.*

@AndroidEntryPoint
class SearchIngredientFragment : Fragment() {

    val viewModel: SearchIngredientViewModel by viewModels()
    private val shareViewModel: ShareResultViewModel by activityViewModels()
    var ingredients = mutableListOf<IngredientOnlyNameView>()

    private val ingredientCallBack = object : OnIngredientItemSelect {
        override fun selectIngredient(adapterPosition: Int) {
            ingredients[adapterPosition].isSelect = !ingredients[adapterPosition].isSelect
            ingredientRecyclerView.adapter?.notifyItemChanged(adapterPosition)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_search_ingredient, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        viewModel.getAllIngredientsOnlyNameView()
        viewModel.ingredientOnlyNameViewMutableLiveData.value?.let {
            ingredients = viewModel.ingredientOnlyNameViewMutableLiveData.value!!
        }
        val adapter = IngredientAdapter(ingredients, ingredientCallBack)
        ingredientRecyclerView.adapter = adapter
        ingredientRecyclerView.layoutManager = LinearLayoutManager(this.context)

        viewModel.ingredientOnlyNameViewMutableLiveData.observe(viewLifecycleOwner)
        {
            ingredients.clear()
            ingredients.addAll(it)
            ingredientRecyclerView.adapter?.notifyDataSetChanged()
        }

        btnResultSearch.setOnClickListener {
            shareViewModel.getChangedIngredients(ingredients)
            navController.navigate(R.id.resultSearchFragment)
        }
    }
}
