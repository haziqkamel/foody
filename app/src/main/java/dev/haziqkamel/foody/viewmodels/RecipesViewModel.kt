package dev.haziqkamel.foody.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dev.haziqkamel.foody.util.Constant.Companion.API_KEY
import dev.haziqkamel.foody.util.Constant.Companion.DEFAULT_DIET_TYPE
import dev.haziqkamel.foody.util.Constant.Companion.DEFAULT_MEAL_TYPE
import dev.haziqkamel.foody.util.Constant.Companion.DEFAULT_RECIPES_NUMBER
import dev.haziqkamel.foody.util.Constant.Companion.QUERY_ADD_INFORMATION
import dev.haziqkamel.foody.util.Constant.Companion.QUERY_API_KEY
import dev.haziqkamel.foody.util.Constant.Companion.QUERY_DIET
import dev.haziqkamel.foody.util.Constant.Companion.QUERY_FILL_INGREDIENTS
import dev.haziqkamel.foody.util.Constant.Companion.QUERY_NUMBER
import dev.haziqkamel.foody.util.Constant.Companion.QUERY_TYPE

class RecipesViewModel(application: Application) : AndroidViewModel(application) {

    fun applyQueries(): HashMap<String, String> {
        val queries: HashMap<String, String> = HashMap()

        queries[QUERY_NUMBER] = DEFAULT_RECIPES_NUMBER
        queries[QUERY_API_KEY] = API_KEY
        queries[QUERY_TYPE] = DEFAULT_MEAL_TYPE
        queries[QUERY_DIET] = DEFAULT_DIET_TYPE
        queries[QUERY_ADD_INFORMATION] = "true"
        queries[QUERY_FILL_INGREDIENTS] = "true"

        return queries
    }

}