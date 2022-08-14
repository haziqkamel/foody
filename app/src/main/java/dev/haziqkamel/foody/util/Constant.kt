package dev.haziqkamel.foody.util

class Constant {
    companion object {

        const val API_KEY = "bb2e851cddae40ba954dbe6d023044ca"
        const val BASE_URL = "https://api.spoonacular.com"
        const val BASE_IMAGE_URL = "https://spoonacular.com/cdn/ingredients_100x100/"

        //Bundle
        const val RECIPE_RESULT_KEY = "recipeBundle"

        // API Query Keys
        const val QUERY_SEARCH = "query"
        const val QUERY_NUMBER = "number"
        const val QUERY_API_KEY = "apiKey"
        const val QUERY_TYPE = "type"
        const val QUERY_DIET = "diet"
        const val QUERY_ADD_INFORMATION = "addRecipeInformation"
        const val QUERY_FILL_INGREDIENTS = "fillIngredients"

        // ROOM Database
        const val DATABASE_NAME = "recipes_db"
        const val RECIPES_TABLE = "recipes_tb"
        const val FAVORITE_RECIPE_TABLE = "favorite_recipe_table"
        const val FOOD_JOKE_TABLE = "food_joke_table"

        // Bottom Sheet and Preferences
        const val DEFAULT_RECIPES_NUMBER = "50"
        const val DEFAULT_MEAL_TYPE = "main course"
        const val DEFAULT_DIET_TYPE = "vegetarian"

        const val PREFERENCES_NAME = "foody_preferences"
        const val PREFERENCES_MEAL_TYPE = "meal_type"
        const val PREFERENCES_MEAL_TYPE_ID = "meal_type_id"
        const val PREFERENCES_DIET_TYPE = "diet_type"
        const val PREFERENCES_DIET_TYPE_ID = "diet_type_id"

        // Network
        const val PREFERENCES_BACK_ONLINE = "back_online"
    }
}