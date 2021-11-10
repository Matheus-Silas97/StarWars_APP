package com.matheussilas97.starwarsapp.view.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matheussilas97.starwarsapp.database.model.FavoriteModel
import com.matheussilas97.starwarsapp.database.repository.FavoriteRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(val repository: FavoriteRepository) : ViewModel() {

    private val mFavoriteList = MutableLiveData<List<FavoriteModel>>()
    val favoriteList: LiveData<List<FavoriteModel>> = mFavoriteList

    fun list() {
        viewModelScope.launch {
            mFavoriteList.value = repository.listFavorites()
        }
    }

    fun deleteFavorite(id: String) {
        viewModelScope.launch {
            val favorite = repository.getLoad(id)
            if (favorite != null) {
                repository.deleteStudents(favorite)
            }
        }
    }


}