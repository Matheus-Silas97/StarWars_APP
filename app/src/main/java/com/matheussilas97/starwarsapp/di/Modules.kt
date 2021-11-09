package com.matheussilas97.starwarsapp.di

import androidx.room.Room
import com.matheussilas97.starwarsapp.api.ApiFactoryFavorites
import com.matheussilas97.starwarsapp.api.Apifactory
import com.matheussilas97.starwarsapp.database.MainDataBase
import com.matheussilas97.starwarsapp.view.charactersdetails.DetailsRepository
import com.matheussilas97.starwarsapp.database.repository.FavoriteRepository
import com.matheussilas97.starwarsapp.view.main.MainRepository
import com.matheussilas97.starwarsapp.view.charactersdetails.CharactersDetailsViewModel
import com.matheussilas97.starwarsapp.view.charactersdetails.SpeciesAdapter
import com.matheussilas97.starwarsapp.view.favorites.FavoriteAdapter
import com.matheussilas97.starwarsapp.view.favorites.FavoriteViewModel
import com.matheussilas97.starwarsapp.view.main.MainAdapter
import com.matheussilas97.starwarsapp.view.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val moduleDb = module {
    single { get<MainDataBase>().favoriteDao() }
    single { Room.databaseBuilder(get(), MainDataBase::class.java, "favDB").allowMainThreadQueries().build() }
}

private val moduleService = module {
    single { Apifactory }
    single { ApiFactoryFavorites }
}

private val moduleRepository = module {
    single { MainRepository(service = get()) }
    single { DetailsRepository(service = get()) }
    single { FavoriteRepository(factoryFavorities = get(), mainDataBase = get()) }
}

private val moduleViewModel = module {
    viewModel { MainViewModel(repository = get()) }
    viewModel { CharactersDetailsViewModel(repository = get(), detailsRepository = get(), favoriteRepository = get())}
    viewModel { FavoriteViewModel(repository = get()) }
}

private val moduleAdapter = module {
    factory { MainAdapter() }
    factory { SpeciesAdapter() }
    factory { FavoriteAdapter() }
}

val listModules = listOf(moduleRepository, moduleViewModel, moduleService, moduleAdapter, moduleDb)