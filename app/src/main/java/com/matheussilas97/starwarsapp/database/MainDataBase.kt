package com.matheussilas97.starwarsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.matheussilas97.starwarsapp.database.dao.FavoriteDAO
import com.matheussilas97.starwarsapp.database.model.FavoriteModel

@Database(entities = [FavoriteModel::class], version = 1)
abstract class MainDataBase : RoomDatabase() {

    abstract val favoriteDao: FavoriteDAO

    companion object {

        @Volatile
        private var INSTANCE: MainDataBase? = null

        fun getInstance(context: Context): MainDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MainDataBase::class.java,
                        "favDB"
                    ).allowMainThreadQueries().fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}