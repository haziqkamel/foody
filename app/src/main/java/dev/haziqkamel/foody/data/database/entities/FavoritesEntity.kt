package dev.haziqkamel.foody.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import dev.haziqkamel.foody.models.foodRecipe.Result
import dev.haziqkamel.foody.util.Constant.Companion.FAVORITE_RECIPE_TABLE

@Entity(tableName = FAVORITE_RECIPE_TABLE)
class FavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var result: Result
)