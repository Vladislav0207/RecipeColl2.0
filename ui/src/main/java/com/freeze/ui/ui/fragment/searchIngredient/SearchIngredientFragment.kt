package com.freeze.ui.ui.fragment.searchIngredient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.freeze.ui.R
import com.freeze.ui.ui.fragment.IngredientAdapter
import com.freeze.ui.ui.fragment.callBack.OnIngredientItemSelect
import com.freeze.ui.ui.model.IngredientOnlyNameView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_ingredient.*
import kotlinx.android.synthetic.main.fragment_start.*
import java.util.*

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
        val adapter = IngredientAdapter(
            ingredients,
            ingredientCallBack
        )
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

        ingredientSearchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (ingredients.none {
                        it.name.toLowerCase(Locale.ROOT).contains(query!!)
                    })
                {
                    Toast.makeText(activity, getString(R.string.ingredientNotFound), Toast.LENGTH_SHORT).show()
                } else
                {
                    adapter.filter.filter(query)
                }
                return false
            }
        })
    }
}
