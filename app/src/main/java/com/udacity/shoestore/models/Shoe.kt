package com.udacity.shoestore.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Shoe(
    var name: String, var size: Double, var company: String, var description: String,
    val images: List<String> = mutableListOf()
) : Parcelable {
    val isValid: Boolean get() = name.isNotEmpty() && size > 0.0

    var sizeText: String
        get() = size.toString()
        set(value) {
            size = value.toDoubleOrNull() ?: 0.0
        }
}