package dev.haziqkamel.foody.models.foodRecipe


import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue


@Parcelize
data class Measures(
    @SerializedName("metric")
    val metric: @RawValue Metric,
    @SerializedName("us")
    val us: @RawValue Us
) : Parcelable