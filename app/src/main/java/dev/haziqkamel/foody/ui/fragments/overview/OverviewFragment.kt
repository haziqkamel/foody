package dev.haziqkamel.foody.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.models.foodRecipe.Result
import kotlinx.android.synthetic.main.fragment_overview.view.*
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_overview, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable("recipeBundle")

        view.overview_imageView.load(myBundle?.image)
        view.title_tv.text = myBundle?.title
        view.like_tv.text = myBundle?.aggregateLikes.toString()
        view.time_tv.text = myBundle?.readyInMinutes.toString()
        view.summary_tv.text = myBundle?.summary
        myBundle?.summary.let {
            val summary = Jsoup.parse(it).text()
            view.summary_tv.text = summary
        }

        // Set color for meal attributes
        if(myBundle?.vegetarian == true) {
            view.vegetarian_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.vegetarian_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
        if(myBundle?.vegan == true) {
            view.vegan_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.vegan_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
        if(myBundle?.glutenFree == true) {
            view.gluten_free_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.gluten_free_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
        if(myBundle?.dairyFree == true) {
            view.dairy_free_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.dairy_free_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
        if(myBundle?.veryHealthy == true) {
            view.healthy_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.healthy_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
        if(myBundle?.cheap == true) {
            view.cheap_iv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            view.cheap_tv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        return view
    }
}