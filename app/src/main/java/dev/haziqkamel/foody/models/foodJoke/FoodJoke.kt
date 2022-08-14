package dev.haziqkamel.foody.models.foodJoke


import com.google.gson.annotations.SerializedName

data class FoodJoke(
    @SerializedName("text")
    val text: String
)