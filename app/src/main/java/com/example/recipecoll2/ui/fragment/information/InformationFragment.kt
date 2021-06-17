package com.example.recipecoll2.ui.fragment.information


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.model.RecipeView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_information.*



class InformationFragment : Fragment() {
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

        viewModel.informationMutableLiveData?.let {
            recipe = viewModel.informationMutableLiveData!!

        }
        pasteForInfoView(recipe)

    }

    private fun pasteForInfoView(recipe:RecipeView){
        Picasso.get().load(recipe.image).into(imageInf)
        val time = getString(R.string.readyInMinutes) + recipe.readyInMinutes.toString()
        val servings = getString(R.string.servings) + recipe.servings.toString()
        nameInf.text = recipe.title
        timeInf.text = time
        servingsInf.text = servings
        var ingredient = getString(R.string.ingredients)+"\n"
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