package com.example.recipecoll2.ui.fragment.information

import android.content.Intent
import android.content.Intent.getIntent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.MainActivity
import com.example.recipecoll2.ui.model.RecipeView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_information.*

class InformationFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: InformationViewModel
    lateinit var recipe: RecipeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val intent = Intent().getIntExtra("recipeID", 0)
        viewModel.getRecipeById(intent)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()


        recipe = viewModel.informationMutableLiveData.value!!

        Picasso.get().load(recipe.image).into(imageInf)
        val time = "readyInMinutes: " + recipe.readyInMinutes.toString()
        val servings = "servings: " + recipe.servings.toString()
        nameInf.text = recipe.title
        timeInf.text = time
        servingsInf.text = servings
        var ingredient = "Ingredients:\n"
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