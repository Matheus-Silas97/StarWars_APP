package com.matheussilas97.starwarsapp.view.charactersdetails

import android.content.Context
import com.matheussilas97.starwarsapp.api.ApiFactoryFavorites
import com.matheussilas97.starwarsapp.api.Apifactory
import com.matheussilas97.starwarsapp.api.response.CharactersDetailsResponse
import com.matheussilas97.starwarsapp.api.response.HomeWorldResponse
import com.matheussilas97.starwarsapp.api.response.SpeciesResponse
import retrofit2.Callback

class DetailsRepository(
    private val service: Apifactory,
) {

    fun getCharacterDetails(url: String, callback: Callback<CharactersDetailsResponse>) {
        service.create().details(url).enqueue(callback)
    }

    fun getHomeWorld(url: String, callback: Callback<HomeWorldResponse>) {
        service.create().getHomeWorld(url).enqueue(callback)
    }

    fun getSpecies(url: String, callback: Callback<SpeciesResponse>) {
        service.create().getSpecies(url).enqueue(callback)
    }
}