package com.matheussilas97.starwarsapp.database.repository

import com.matheussilas97.starwarsapp.api.ApiFactoryFavorites
import com.matheussilas97.starwarsapp.api.response.FavoritesResponse
import com.matheussilas97.starwarsapp.database.MainDataBase
import com.matheussilas97.starwarsapp.database.dao.FavoriteDAO
import com.matheussilas97.starwarsapp.database.model.FavoriteModel
import retrofit2.Callback

class FavoriteRepository(private val factoryFavorities: ApiFactoryFavorites, private val favoriteDao: FavoriteDAO) {

    suspend fun listFavorites(): List<FavoriteModel> = favoriteDao.getAllFavorites()

    suspend fun addStudents(favorite: FavoriteModel): Boolean = favoriteDao.insertFavorite(favorite) > 0

    suspend fun deleteStudents(favorite: FavoriteModel) = favoriteDao.deleteFavorite(favorite)

    suspend fun getLoad(id: String): FavoriteModel? = favoriteDao.load(id)

    fun isFavorite(url: String): Boolean = favoriteDao.isFavorite(url)


    fun postFavorite(id: String, callback: Callback<FavoritesResponse>) {
        factoryFavorities.create().postFavorites(id).enqueue(callback)
    }

}