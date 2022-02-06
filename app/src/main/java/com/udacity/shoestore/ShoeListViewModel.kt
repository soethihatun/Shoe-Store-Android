package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeListViewModel : ViewModel() {

    private val _shoes = MutableLiveData<List<Shoe>>(emptyList())
    val shoes: LiveData<List<Shoe>> = _shoes

    private val _shoeAdded = MutableLiveData(false)
    val shoeAdded: LiveData<Boolean> = _shoeAdded

    private val _errorMessage = MutableLiveData<Int>()
    val errorMessage: LiveData<Int> = _errorMessage

    fun addShoe(name: String, size: Double, company: String, description: String) {
        _shoes.value?.let { shoes ->
            val shoe = Shoe(name, size, company, description)
            if (shoe.isValid) {
                val list = shoes.toMutableList().also { it.add(shoe) }
                _shoes.value = list
                _shoeAdded.value = true
            } else {
                _errorMessage.value = R.string.invalid_information_message
            }
        }
    }

    fun shoeAddedComplete() {
        _shoeAdded.value = false
    }

    fun errorMessageComplete() {
        _errorMessage.value = null
    }
}
