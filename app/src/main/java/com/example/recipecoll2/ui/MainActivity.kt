package com.example.recipecoll2.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.recipecoll2.R
import com.example.recipecoll2.ui.viewModel.RecipeViewModel
import com.example.recipecoll2.ui.viewModel.RecipeViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var viewModel: RecipeViewModel
    @Inject lateinit var factory: RecipeViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        setSupportActionBar(toolbar)
        navController = findNavController(R.id.navHost)
        setupActionBarWithNavController(navController,drawer_layout)
        toolbar.setupWithNavController(navController,drawer_layout)
        nav_view.setupWithNavController(navController)








        viewModel = ViewModelProvider(this, factory).get(RecipeViewModel::class.java)
        viewModel.recipeLive.value = mutableListOf()

    }


    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return NavigationUI.onNavDestinationSelected(item, navController) ||
                super.onOptionsItemSelected(item)
    }
}