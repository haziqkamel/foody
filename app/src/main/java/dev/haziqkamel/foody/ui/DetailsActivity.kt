package dev.haziqkamel.foody.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.navArgs
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.adapters.PagerAdapter
import dev.haziqkamel.foody.data.database.entities.FavoritesEntity
import dev.haziqkamel.foody.ui.fragments.ingredients.IngredientFragment
import dev.haziqkamel.foody.ui.fragments.instructions.InstructionsFragment
import dev.haziqkamel.foody.ui.fragments.overview.OverviewFragment
import dev.haziqkamel.foody.util.Constant.Companion.RECIPE_RESULT_KEY
import dev.haziqkamel.foody.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_details.*

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val args by navArgs<DetailsActivityArgs>()
    private val mainViewModel: MainViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, R.color.white))
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val fragments = ArrayList<Fragment>()
        fragments.add(OverviewFragment())
        fragments.add(IngredientFragment())
        fragments.add(InstructionsFragment())

        val titles = ArrayList<String>()
        titles.add("Overview")
        titles.add("Ingredient")
        titles.add("Instructions")

        val resultBundle = Bundle()
        resultBundle.putParcelable(RECIPE_RESULT_KEY, args.result)

        val adapter = PagerAdapter(
            resultBundle,
            fragments,
            titles,
            supportFragmentManager,
        )

        viewPager.adapter = adapter
        tab_layout.setupWithViewPager(viewPager)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        } else if (item.itemId == R.id.save_to_favorites_menu) {
            saveToFavorites(item)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun saveToFavorites(item: MenuItem) {
        val favoriteEntity = FavoritesEntity(0, args.result)
        mainViewModel.insertFavoriteRecipe(favoriteEntity)
        changeMenuItemColor(item, R.color.yellow)
        showSnackBar("Recipes Saved!")
    }

    private fun showSnackBar(message: String) {
        Snackbar.make(details_layout, message, Snackbar.LENGTH_SHORT).setAction("OK") {}.show()
    }

    private fun changeMenuItemColor(item: MenuItem, color: Int) {
        item.icon.setTint(ContextCompat.getColor(this, color))
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.details_menu, menu)
        return true
    }
}