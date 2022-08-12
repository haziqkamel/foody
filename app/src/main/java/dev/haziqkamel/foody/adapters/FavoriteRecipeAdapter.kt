package dev.haziqkamel.foody.adapters

import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.data.database.entities.FavoritesEntity
import dev.haziqkamel.foody.databinding.FavoriteRecipeRowLayoutBinding
import dev.haziqkamel.foody.ui.fragments.favoriteRecipes.FavoriteRecipesFragmentDirections
import dev.haziqkamel.foody.util.RecipesDiffUtil
import kotlinx.android.synthetic.main.favorite_recipe_row_layout.view.*

class FavoriteRecipeAdapter(
    private val requireActivity: FragmentActivity
) : RecyclerView.Adapter<FavoriteRecipeAdapter.MyViewHolder>(),
    ActionMode.Callback {

    private var favoriteRecipes = emptyList<FavoritesEntity>()

    private var multiSelection = false
    private var selectedRecipes = arrayListOf<FavoritesEntity>()
    private var myViewHolders = arrayListOf<MyViewHolder>()

    private lateinit var mActionMode: ActionMode

    class MyViewHolder(private val binding: FavoriteRecipeRowLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(favoritesEntity: FavoritesEntity) {
            binding.favoriteEntity = favoritesEntity
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = FavoriteRecipeRowLayoutBinding.inflate(layoutInflater, parent, false)

                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        myViewHolders.add(holder)

        val currentRecipe = favoriteRecipes[position]
        holder.bind(currentRecipe)

        /**
         * Single Click Listener
         * */
        holder.itemView.favoriteRecipesRowLayout.setOnClickListener {

            if (multiSelection) {
                applySelection(holder, currentRecipe)
            } else {
                val action =
                    FavoriteRecipesFragmentDirections.actionFavoriteRecipesFragmentToDetailsActivity(
                        currentRecipe.result
                    )

                holder.itemView.findNavController().navigate(action)
            }
        }

        /**
         * Long Click Listener
         * */
        holder.itemView.favoriteRecipesRowLayout.setOnLongClickListener {

            if (!multiSelection) {
                multiSelection = true
                requireActivity.startActionMode(this)
                applySelection(holder, currentRecipe)
                true
            } else {
                multiSelection = false
                false
            }


        }
    }

    private fun applySelection(holder: MyViewHolder, currentRecipe: FavoritesEntity) {
        if (selectedRecipes.contains(currentRecipe)) {
            selectedRecipes.remove(currentRecipe)
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
            applyActionModeTitle()
        } else {
            selectedRecipes.add(currentRecipe)
            changeRecipeStyle(holder, R.color.cardBackgroundLightColor, R.color.colorPrimary)
            applyActionModeTitle()
        }
    }

    private fun changeRecipeStyle(holder: MyViewHolder, backgroundColor: Int, strokeColor: Int) {
        holder.itemView.favoriteRecipesRowLayout.setBackgroundColor(
            ContextCompat.getColor(requireActivity, backgroundColor)
        )
        holder.itemView.recipesCard.strokeColor =
            ContextCompat.getColor(requireActivity, strokeColor)
    }

    private fun applyActionModeTitle() {
        when (selectedRecipes.size) {
            0 -> {
                mActionMode.finish()
            }
            1 -> {
                mActionMode.title = "${selectedRecipes.size} item selected"
            }
            else -> {
                mActionMode.title = "${selectedRecipes.size} items selected"
            }
        }
    }

    override fun getItemCount(): Int {
        return favoriteRecipes.size
    }

    fun setData(newFavoriteRecipe: List<FavoritesEntity>) {
        val favoriteRecipesDiffUtil = RecipesDiffUtil(favoriteRecipes, newFavoriteRecipe)
        val diffUtilResult = DiffUtil.calculateDiff(favoriteRecipesDiffUtil)
        favoriteRecipes = newFavoriteRecipe
        diffUtilResult.dispatchUpdatesTo(this)
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        mode?.menuInflater?.inflate(R.menu.favorite_contextual_menu, menu)
        mActionMode = mode!!
        applyStatusBarColor(R.color.contextualStatusBarColor)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return true
    }

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        return true
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        myViewHolders.forEach { holder ->
            changeRecipeStyle(holder, R.color.cardBackgroundColor, R.color.strokeColor)
        }
        multiSelection = false
        selectedRecipes.clear()
        applyStatusBarColor(R.color.statusBarColor)
    }

    private fun applyStatusBarColor(color: Int) {
        requireActivity.window.statusBarColor = ContextCompat.getColor(requireActivity, color)
    }
}