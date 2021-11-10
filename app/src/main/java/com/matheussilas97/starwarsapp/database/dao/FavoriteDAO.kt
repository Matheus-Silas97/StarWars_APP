package com.matheussilas97.starwarsapp.database.dao

import androidx.room.*
import com.matheussilas97.starwarsapp.database.model.FavoriteModel

@Dao
interface FavoriteDAO {

    @Query("SELECT * FROM favorites")
    suspend fun getAllFavorites(): List<FavoriteModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(item: FavoriteModel): Long

    @Delete
    suspend fun deleteFavorite(item: FavoriteModel)

    @Query("SELECT * FROM favorites WHERE id = :id")
    suspend fun load(id: String): FavoriteModel?

    @Query("SELECT EXISTS(SELECT * FROM favorites WHERE url = :url)")
    fun isFavorite(url: String): Boolean

}