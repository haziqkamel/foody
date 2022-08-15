package dev.haziqkamel.foody.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.adapters.IngredientsAdapter
import dev.haziqkamel.foody.databinding.FragmentIngredientBinding
import dev.haziqkamel.foody.models.foodRecipe.Result
import dev.haziqkamel.foody.util.Constant.Companion.RECIPE_RESULT_KEY

class IngredientFragment : Fragment() {

    // Step to connect the dot between IngredientsAdapter into IngredientsFragment
    // 1. Initialize ingredient adapter
    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    private var _binding: FragmentIngredientBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentIngredientBinding.inflate(inflater, container, false)

        // 2. Get the arguments from DetailsActivity class
        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        //4. init fun
        setupRecyclerView()
        // 5. set data of the adapter
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }

        return binding.root
    }

    // 3. Create fun to setup RecyclerView
    private fun setupRecyclerView() {
        binding.ingredientsRv.adapter = mAdapter
        binding.ingredientsRv.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}