package com.matheussilas97.starwarsapp.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.matheussilas97.starwarsapp.database.dao.FavoriteDAO
import com.matheussilas97.starwarsapp.database.model.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 1)
abstract class MainDataBase : RoomDatabase() {

    abstract fun favoriteDao(): FavoriteDAO

}