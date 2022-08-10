package dev.haziqkamel.foody.ui.fragments.ingredients

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.adapters.IngredientsAdapter
import dev.haziqkamel.foody.models.foodRecipe.Result
import dev.haziqkamel.foody.util.Constant.Companion.RECIPE_RESULT_KEY
import kotlinx.android.synthetic.main.fragment_ingredient.view.*

class IngredientFragment : Fragment() {

    // Step to connect the dot between IngredientsAdapter into IngredientsFragment
    // 1. Initialize ingredient adapter
    private val mAdapter: IngredientsAdapter by lazy { IngredientsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_ingredient, container, false)

        // 2. Get the arguments from DetailsActivity class
        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        //4. init fun
        setupRecyclerView(view)
        // 5. set data of the adapter
        myBundle?.extendedIngredients?.let { mAdapter.setData(it) }

        return view
    }

    // 3. Create fun to setup RecyclerView
    private fun setupRecyclerView(view: View) {
        view.ingredients_rv.adapter = mAdapter
        view.ingredients_rv.layoutManager = LinearLayoutManager(requireContext())
    }
}