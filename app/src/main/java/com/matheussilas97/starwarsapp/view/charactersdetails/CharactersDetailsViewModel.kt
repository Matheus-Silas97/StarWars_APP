package com.matheussilas97.starwarsapp.view.charactersdetails

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.matheussilas97.starwarsapp.R
import com.matheussilas97.starwarsapp.api.response.FavoriteErrorResponse
import com.matheussilas97.starwarsapp.api.response.FavoritesResponse
import com.matheussilas97.starwarsapp.api.response.CharactersDetailsResponse
import com.matheussilas97.starwarsapp.api.response.HomeWorldResponse
import com.matheussilas97.starwarsapp.api.response.SpeciesResponse
import com.matheussilas97.starwarsapp.database.model.FavoriteModel
import com.matheussilas97.starwarsapp.database.repository.FavoriteRepository
import com.matheussilas97.starwarsapp.utils.Utils
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharactersDetailsViewModel(
    private val repository: FavoriteRepository,
    private val detailsRepository: DetailsRepository,
    private val favoriteRepository: FavoriteRepository
) : ViewModel() {

    private var mSaveStatus = MutableLiveData<Boolean>()
    val saveStatus: LiveData<Boolean> = mSaveStatus

    private var mSaveFavorite = MutableLiveData<Boolean>()
    val saveFavorite: LiveData<Boolean> = mSaveFavorite


    fun getDetails(url: String, context: Context): MutableLiveData<CharactersDetailsResponse> {
        val result = MutableLiveData<CharactersDetailsResponse>()
        val dialog = Utils.showLoading(context, R.string.loading)
        detailsRepository.getCharacterDetails(url, object : Callback<CharactersDetailsResponse> {
            override fun onResponse(
                call: Call<CharactersDetailsResponse>,
                response: Response<CharactersDetailsResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = response.body()
                    dialog.dismiss()
                } else {
                    result.value = null!!
                    dialog.dismiss()
                }
            }

            override fun onFailure(call: Call<CharactersDetailsResponse>, t: Throwable) {
                result.value = null!!
                dialog.dismiss()
            }

        })

        return result
    }

    fun getHomeWorld(url: String): MutableLiveData<HomeWorldResponse> {
        val result = MutableLiveData<HomeWorldResponse>()
        detailsRepository.getHomeWorld(url, object : Callback<HomeWorldResponse> {
            override fun onResponse(
                call: Call<HomeWorldResponse>,
                response: Response<HomeWorldResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = response.body()
                }
            }

            override fun onFailure(call: Call<HomeWorldResponse>, t: Throwable) {

            }

        })

        return result
    }

    fun getSpecies(url: String): MutableLiveData<SpeciesResponse> {
        val result = MutableLiveData<SpeciesResponse>()

        detailsRepository.getSpecies(url, object : Callback<SpeciesResponse> {
            override fun onResponse(
                call: Call<SpeciesResponse>,
                response: Response<SpeciesResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = response.body()
                } else {
                    val a = ""
                }
            }

            override fun onFailure(call: Call<SpeciesResponse>, t: Throwable) {
                val a = ""
            }

        })

        return result
    }

    fun postFavorite(id: String): MutableLiveData<String> {
        val result = MutableLiveData<String>()

        favoriteRepository.postFavorite(id, object : Callback<FavoritesResponse> {
            override fun onResponse(
                call: Call<FavoritesResponse>,
                response: Response<FavoritesResponse>
            ) {
                if (response.isSuccessful) {
                    result.value = response.body()?.message
                    mSaveStatus.value = true
                } else {
                    val errorMessage =
                        Gson().fromJson(
                            response.errorBody()?.string(),
                            FavoriteErrorResponse::class.java
                        )
                    val error = errorMessage.errorMessage
                    result.value = error
                    mSaveStatus.value = false
                }
            }

            override fun onFailure(call: Call<FavoritesResponse>, t: Throwable) {
                mSaveStatus.value = true
            }

        })

        return result
    }

    fun saveClass(favorite: FavoriteModel) {
        mSaveFavorite.value = repository.addStudents(favorite)
    }

    fun isFavorite(url: String): Boolean = repository.isFavorite(url)

    fun deleteFavorite(id: String) {
        val favorite = repository.getLoad(id)
        if (favorite != null) {
            repository.deleteStudents(favorite)
        }
    }
}