package com.example.recipecoll2.ui.fragment.information

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
import com.example.recipecoll2.ui.viewModel.RecipeViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_information.*

class InformationFragment : Fragment() {
    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel= ViewModelProvider(activity as MainActivity).get(RecipeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_information, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = findNavController()

        Picasso.get().load(viewModel.showRecipe!!.image).into(imageInf)
        val time = "readyInMinutes: " + viewModel.showRecipe!!.readyInMinutes.toString()
        val servings = "servings: " + viewModel.showRecipe!!.servings.toString()
        nameInf.text=viewModel.showRecipe!!.title
        timeInf.text = time
        servingsInf.text =servings
        var ingredient = "Ingredients:\n"
        viewModel.showRecipe!!.extendedIngredients.forEach {
           ingredient += it.nameClean + ": " + it.amount + " " + it.unit + "\n"
        }
        ingredientsInf.text=ingredient
        instructionInf.text = viewModel.showRecipe!!.instructions.replace(
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