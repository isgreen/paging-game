package br.com.isgreen.study.paginggame.ui

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import br.com.isgreen.study.paginggame.base.BaseContract
import br.com.isgreen.study.paginggame.model.game.Game
import br.com.isgreen.study.paginggame.model.game.GameAdapterItem
import kotlinx.coroutines.flow.Flow

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

interface GameContract {

    interface ViewModel : BaseContract.ViewModel {
        fun getGames(): Flow<PagingData<GameAdapterItem>>
        fun getGamesLiveData(): LiveData<PagingData<GameAdapterItem>>
    }

    interface Repository {
        fun getGames(): Flow<PagingData<Game>>
        fun getGamesLiveData(): LiveData<PagingData<Game>>
    }
}