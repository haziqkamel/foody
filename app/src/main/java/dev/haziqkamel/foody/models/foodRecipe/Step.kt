package dev.haziqkamel.foody.models.foodRecipe


import com.google.gson.annotations.SerializedName

data class Step(
    @SerializedName("ingredients")
    val ingredients: List<Ingredient>,
    @SerializedName("length")
    val length: Length,
    @SerializedName("number")
    val number: Int,
    @SerializedName("step")
    val step: String
)