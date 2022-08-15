package dev.haziqkamel.foody.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.databinding.IngredientsRowLayoutBinding
import dev.haziqkamel.foody.models.foodRecipe.ExtendedIngredient
import dev.haziqkamel.foody.util.Constant.Companion.BASE_IMAGE_URL
import dev.haziqkamel.foody.util.RecipesDiffUtil

// 1. Extend class to RecyclerView.Adapter of type MyViewHolder(created later)
class IngredientsAdapter : RecyclerView.Adapter<IngredientsAdapter.MyViewHolder>() {

    // 4. Create an empty array
    private var ingredientList = emptyList<ExtendedIngredient>()

    // 2. Extend MyViewHolder(binding) class to RecyclerView.ViewHolder(view)
    class MyViewHolder(val binding: IngredientsRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

    // 3. Implement all members override, return this override a MyViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            IngredientsRowLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    // 6. Bind the data
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.ingredientIv.load(BASE_IMAGE_URL + ingredientList[position].image) {
            crossfade(600)
            error(R.drawable.ic_error_placeholder)
        }
        holder.binding.ingredientName.text = ingredientList[position].name.uppercase()
        holder.binding.ingredientAmount.text = ingredientList[position].amount.toString()
        holder.binding.ingredientUnit.text = ingredientList[position].unit
        holder.binding.ingredientConsistency.text = ingredientList[position].consistency
        holder.binding.ingredientOriginal.text = ingredientList[position].original
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