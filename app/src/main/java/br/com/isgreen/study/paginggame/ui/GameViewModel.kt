package br.com.isgreen.study.paginggame.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.insertSeparators
import androidx.paging.map
import br.com.isgreen.study.paginggame.base.BaseViewModel
import br.com.isgreen.study.paginggame.helper.exception.ExceptionHandlerHelper
import br.com.isgreen.study.paginggame.model.game.GameAdapterItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created by Éverdes Soares on 06/15/2021.
 */

class GameViewModel(
    exceptionHandlerHelper: ExceptionHandlerHelper,
    private val repository: GameContract.Repository
) : BaseViewModel(exceptionHandlerHelper), GameContract.ViewModel {

    override fun getGames(): Flow<PagingData<GameAdapterItem>> {
        return repository.getGames()
            .map { pagingData -> pagingData.map { GameAdapterItem.GameItem(it) } }
            .map {
                it.insertSeparators { before, after ->
                    if (after == null) {
                        return@insertSeparators null
                    }

                    if (before == null) {
                        return@insertSeparators GameAdapterItem.SeparatorItem("${after.roundedRatingCount} stars")
                    }

                    if (before.roundedRatingCount > after.roundedRatingCount) {
                        if (after.roundedRatingCount >= 1) {
                            return@insertSeparators GameAdapterItem.SeparatorItem("${after.roundedRatingCount} stars")
                        } else {
                            return@insertSeparators GameAdapterItem.SeparatorItem("No stars")
                        }
                    } else {
                        null
                    }
                }
            }
            .cachedIn(viewModelScope)
    }
}