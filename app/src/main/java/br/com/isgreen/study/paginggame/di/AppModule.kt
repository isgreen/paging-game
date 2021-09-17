package br.com.isgreen.study.paginggame.di

import br.com.isgreen.study.paginggame.datasource.Api
import br.com.isgreen.study.paginggame.helper.api.ApiHelper
import br.com.isgreen.study.paginggame.helper.api.ApiHelperImpl
import br.com.isgreen.study.paginggame.helper.exception.ExceptionHandlerHelper
import br.com.isgreen.study.paginggame.helper.exception.ExceptionHandlerHelperImpl
import org.koin.dsl.module

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

val appModule = module {
    single { Api.Factory.create() }
    single<ApiHelper> { ApiHelperImpl(get()) }
    single<ExceptionHandlerHelper> { ExceptionHandlerHelperImpl(get()) }
}