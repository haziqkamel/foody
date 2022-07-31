package dev.haziqkamel.foody.data

import dev.haziqkamel.foody.data.network.FoodRecipesApi
import dev.haziqkamel.foody.models.foodRecipe.FoodRecipe
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val foodRecipesApi: FoodRecipesApi,
) {
    suspend fun getRecipes(queries: Map<String, String>): Response<FoodRecipe> {
        return foodRecipesApi.getRecipes(queries)
    }
}