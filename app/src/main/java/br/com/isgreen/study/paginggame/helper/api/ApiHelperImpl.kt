package br.com.isgreen.study.paginggame.helper.api

import br.com.isgreen.study.paginggame.BuildConfig
import br.com.isgreen.study.paginggame.datasource.Api
import br.com.isgreen.study.paginggame.model.game.GameResult

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

class ApiHelperImpl(private val api: Api) : ApiHelper {

    //region Game
    override suspend fun getGames(page: Int): GameResult =
        api.getGames(page, BuildConfig.API_KEY, "-rating")
    //endregion Game
}