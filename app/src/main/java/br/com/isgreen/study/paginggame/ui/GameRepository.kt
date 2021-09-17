package br.com.isgreen.study.paginggame.ui

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import br.com.isgreen.study.paginggame.datasource.GamePagingSource
import br.com.isgreen.study.paginggame.helper.api.ApiHelper
import br.com.isgreen.study.paginggame.model.game.Game
import kotlinx.coroutines.flow.Flow

/**
 * Created by Éverdes Soares on 06/15/2021.
 */

class GameRepository(private val apiHelper: ApiHelper) : GameContract.Repository {

    override fun getGames(): Flow<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GamePagingSource(apiHelper = apiHelper)
            }
        ).flow
    }

    override fun getGamesLiveData(): LiveData<PagingData<Game>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                GamePagingSource(apiHelper = apiHelper)
            }
        ).liveData
    }
}