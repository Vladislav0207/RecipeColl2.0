package com.example.recipecoll2.ui.fragment.information

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.MainActivity
import com.example.recipecoll2.ui.model.RecipeView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_information.*
import javax.inject.Inject



class InformationFragment : Fragment() {
    lateinit var navController: NavController
    val viewModel: InformationViewModel by activityViewModels()
    lateinit var recipe : RecipeView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        viewModel.informationMutableLiveData?.let {
            recipe = viewModel.informationMutableLiveData!!

        }
        pasteForInfoView(recipe)
//        viewModel.informationMutableLiveData.observe(viewLifecycleOwner)
//        {
//            val recipe = it
//            pasteForInfoView(recipe)
//        }
    }

    fun pasteForInfoView(recipe:RecipeView){
        Picasso.get().load(recipe.image).into(imageInf)
        val time = getString(R.string.readyInMinutes) + recipe.readyInMinutes.toString()
        val servings = getString(R.string.servings) + recipe.servings.toString()
        nameInf.text = recipe.title
        timeInf.text = time
        servingsInf.text = servings
        var ingredient = getString(R.string.ingredients)+":\n"
        recipe.extendedIngredients.forEach {
            ingredient += it.nameClean + ": " + it.amount + " " + it.unit + "\n"
        }
        ingredientsInf.text = ingredient
        instructionInf.text = recipe.instructions.replace(
            "</li><li>",
            "\n"
        ).replace(
            "<ol><li>",
            "  "
        ).replace(
            "</li></ol>",
            ""
        )
    }
}