package br.com.isgreen.study.paginggame.ui

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Éverdes Soares on 06/15/2021.
 */

val gameModule = module {
    viewModel { GameViewModel(get(), get()) }
    factory<GameContract.Repository> { GameRepository(get()) }
}