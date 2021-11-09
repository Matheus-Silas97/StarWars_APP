package com.matheussilas97.starwarsapp.view.main

import com.matheussilas97.starwarsapp.api.Apifactory
import com.matheussilas97.starwarsapp.api.response.CharactersListResponse
import retrofit2.Callback

class MainRepository(private val service: Apifactory) {

     fun getListCharacter(page: Long, callback: Callback<CharactersListResponse>){
        service.create().listCharacters(page).enqueue(callback)
    }

}