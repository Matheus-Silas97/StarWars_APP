package com.matheussilas97.starwarsapp.database.repository

import com.matheussilas97.starwarsapp.api.ApiFactoryFavorites
import com.matheussilas97.starwarsapp.api.response.FavoritesResponse
import com.matheussilas97.starwarsapp.database.MainDataBase
import com.matheussilas97.starwarsapp.database.model.FavoriteModel
import retrofit2.Callback

class FavoriteRepository(private val factoryFavorities: ApiFactoryFavorites, private val mainDataBase: MainDataBase) {


    fun listFavorites(): List<FavoriteModel> = mainDataBase.favoriteDao().getAllFavorites()

    fun addStudents(favorite: FavoriteModel): Boolean = mainDataBase.favoriteDao().insertFavorite(favorite) > 0

    fun deleteStudents(favorite: FavoriteModel) = mainDataBase.favoriteDao().deleteFavorite(favorite)

    fun getLoad(id: String): FavoriteModel? = mainDataBase.favoriteDao().load(id)

    fun isFavorite(url: String): Boolean = mainDataBase.favoriteDao().isFavorite(url)


    fun postFavorite(id: String, callback: Callback<FavoritesResponse>) {
        factoryFavorities.create().postFavorites(id).enqueue(callback)
    }

}