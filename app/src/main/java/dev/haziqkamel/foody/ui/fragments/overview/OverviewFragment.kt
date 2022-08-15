package dev.haziqkamel.foody.ui.fragments.overview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import coil.load
import dev.haziqkamel.foody.R
import dev.haziqkamel.foody.databinding.FragmentOverviewBinding
import dev.haziqkamel.foody.models.foodRecipe.Result
import dev.haziqkamel.foody.util.Constant.Companion.RECIPE_RESULT_KEY
import org.jsoup.Jsoup

class OverviewFragment : Fragment() {

    private var _binding: FragmentOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentOverviewBinding.inflate(inflater, container, false)

        val args = arguments
        val myBundle: Result? = args?.getParcelable(RECIPE_RESULT_KEY)

        binding.overviewImageView.load(myBundle?.image)
        binding.titleTv.text = myBundle?.title
        binding.likeTv.text = myBundle?.aggregateLikes.toString()
        binding.timeTv.text = myBundle?.readyInMinutes.toString()
        binding.summaryTv.text = myBundle?.summary
        myBundle?.summary.let {
            val summary = Jsoup.parse(it).text()
            binding.summaryTv.text = summary
        }

        // Set color for meal attributes
        if (myBundle?.vegetarian == true) {
            binding.vegetarianIv.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.vegetarianTv.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (myBundle?.vegan == true) {
            binding.veganIv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.veganTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
        if (myBundle?.glutenFree == true) {
            binding.glutenFreeIv.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.glutenFreeTv.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (myBundle?.dairyFree == true) {
            binding.dairyFreeIv.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.dairyFreeTv.setTextColor(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
        }
        if (myBundle?.veryHealthy == true) {
            binding.healthyIv.setColorFilter(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.green
                )
            )
            binding.healthyTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }
        if (myBundle?.cheap == true) {
            binding.cheapIv.setColorFilter(ContextCompat.getColor(requireContext(), R.color.green))
            binding.cheapTv.setTextColor(ContextCompat.getColor(requireContext(), R.color.green))
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}