package com.matheussilas97.starwarsapp.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class FavoriteModel(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val idName: String,

    @ColumnInfo(name = "url")
    val url: String

)