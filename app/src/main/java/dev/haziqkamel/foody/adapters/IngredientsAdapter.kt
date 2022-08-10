package dev.haziqkamel.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.models.foodRecipe.ExtendedIngredient
import dev.haziqkamel.foody.util.Constant.Companion.BASE_IMAGE_URL
import dev.haziqkamel.foody.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.ingredients_row_layout.view.*

// 1. Extend class to RecyclerView.Adapter of type MyViewHolder(created later)
class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    // 4. Create an empty array
    private var ingredientList = emptyList<ExtendedIngredient>()

    // 2. Extend MyViewHolder(view) class to RecyclerView.ViewHolder(view)
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    // 3. Implement all members override, return this override a MyViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.ingredients_row_layout, parent, false)
        )
    }

    // 6. Bind the data
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.ingredient_iv.load(BASE_IMAGE_URL + ingredientList[position].image) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
        holder.itemView.ingredient_name.text = ingredientList[position].name.uppercase()
        holder.itemView.ingredient_amount.text = ingredientList[position].amount.toString()
        holder.itemView.ingredient_unit.text = ingredientList[position].unit
        holder.itemView.ingredient_consistency.text = ingredientList[position].consistency
        holder.itemView.ingredient_original.text = ingredientList[position].original
    }

    // 5. return list size
    override fun getItemCount(): Int {
        return ingredientList.size
    }

    // 7. Create new function to do diff util and updates the adapter
    fun setData(newIngredients: List<ExtendedIngredient>) {
        val ingredientDiffUtil = RecipesDiffUtil(ingredientList, newIngredients)
        val diffUtilResult = DiffUtil.calculateDiff(ingredientDiffUtil)
        ingredientList = newIngredients
        diffUtilResult.dispatchUpdatesTo(this)
    }
}