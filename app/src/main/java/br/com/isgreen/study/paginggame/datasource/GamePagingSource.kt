package br.com.isgreen.study.paginggame.datasource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import br.com.isgreen.study.paginggame.helper.api.ApiHelper
import br.com.isgreen.study.paginggame.model.game.Game

/**
 * Created by Éverdes Soares on 06/15/2021.
 */

class GamePagingSource(
    private val apiHelper: ApiHelper
) : PagingSource<Int, Game>() {

    companion object {
        const val FIRST_PAGE = 1
    }

    override fun getRefreshKey(state: PagingState<Int, Game>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Game> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val response = apiHelper.getGames(page)
            val nextPage = if (response.next.isNullOrEmpty()) null else page + 1

            LoadResult.Page(
                data = response.results,
                prevKey = if (page == FIRST_PAGE) null else page - 1,
                nextKey = nextPage
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}