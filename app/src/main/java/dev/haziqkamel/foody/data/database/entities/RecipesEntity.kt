package dev.haziqkamel.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.haziqkamel.foody.models.foodRecipe.FoodRecipe
import dev.haziqkamel.foody.util.Constant.Companion.RECIPES_TABLE

@Entity(tableName = RECIPES_TABLE)
class RecipesEntity(
    var foodRecipe: FoodRecipe
) {
    @PrimaryKey(autoGenerate = false)
    var id: Int = 0
}