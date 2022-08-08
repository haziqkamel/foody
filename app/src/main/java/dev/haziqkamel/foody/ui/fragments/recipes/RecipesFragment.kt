package dev.haziqkamel.foody.ui.fragments.recipes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import dev.haziqkamel.foody.viewmodels.MainViewModel
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.adapters.RecipesAdapter
import dev.haziqkamel.foody.util.NetworkResult
import dev.haziqkamel.foody.viewmodels.RecipesViewModel
import kotlinx.android.synthetic.main.fragment_recipes.view.*
import kotlinx.coroutines.launch


@AndroidEntryPoint
class RecipesFragment : Fragment() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var recipesViewModel: RecipesViewModel
    private val mAdapter by lazy { RecipesAdapter() }
    private lateinit var mView: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        recipesViewModel = ViewModelProvider(requireActivity()).get(RecipesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_recipes, container, false)

        setupRecyclerView()
        readDatabase()

        return mView
    }

    private fun readDatabase() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
                if (database.isNotEmpty()) {
                    mAdapter.setData(database[0].foodRecipe)
                    showShimmerEffect(false)
                } else {
                    requestApiData()
                }
            }
        }
    }

    private fun requestApiData() {
        mainViewModel.getRecipes(recipesViewModel.applyQueries())
        mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->

            when (response) {
                is NetworkResult.Success -> {
                    showShimmerEffect(false)
                    response.data?.let { mAdapter.setData(it) }
                }
                is NetworkResult.Error -> {
                    showShimmerEffect(false)
                    loadDataFromCache() // If user see error, load data from cache
                    Toast.makeText(
                        requireContext(),
                        response.message.toString(),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is NetworkResult.Loading -> {
                    showShimmerEffect(true)
                }
            }
        }
    }


    private fun setupRecyclerView() {
        mView.recyclerView.adapter = mAdapter
        mView.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        showShimmerEffect(true)
    }

    private fun loadDataFromCache() {
        lifecycleScope.launch {
            mainViewModel.readRecipes.observe(viewLifecycleOwner) { database ->
                mAdapter.setData(database[0].foodRecipe)
            }
        }
    }

    private fun showShimmerEffect(showShimmer: Boolean) {
        mView.shimmerLayout.visibility = if (showShimmer) View.VISIBLE else View.GONE
        mView.shimmerLayout.showShimmer(showShimmer)
    }
}

