package br.com.isgreen.study.paginggame.helper.api

import br.com.isgreen.study.paginggame.model.game.GameResult

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

interface ApiHelper {

    //region Game
    suspend fun getGames(page: Int): GameResult
    //endregion Game
}