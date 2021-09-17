package br.com.isgreen.study.paginggame.datasource

import br.com.isgreen.study.paginggame.BuildConfig
import br.com.isgreen.study.paginggame.model.game.Game
import br.com.isgreen.study.paginggame.model.game.GameResult
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.concurrent.TimeUnit

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

interface Api {

    class Factory {

        companion object {
            fun create(): Api {
                val okHttpClient: OkHttpClient.Builder =
                    OkHttpClient.Builder()
                        .connectTimeout(1, TimeUnit.MINUTES)
                        .readTimeout(1, TimeUnit.MINUTES)

                val interceptor = HttpLoggingInterceptor()
                interceptor.level = HttpLoggingInterceptor.Level.BODY

                val requestInterceptor = RequestInterceptor()
                okHttpClient.addInterceptor(interceptor)
                    .addInterceptor(requestInterceptor)
                    .build()

                val retrofit = Retrofit.Builder()
                    .baseUrl(BuildConfig.BASE_URL)
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(okHttpClient.build())
                    .build()

                return retrofit.create(Api::class.java)
            }
        }
    }

    //region Game
    @GET(ApiConstant.FETCH_GAMES)
    suspend fun getGames(
        @Query("page") page: Int,
        @Query("key") apiKey: String,
        @Query("ordering") ordering: String
    ): GameResult

    @GET(ApiConstant.FETCH_GAME_DETAIL)
    suspend fun getGameDetail(
        @Path("gameId") gameId: Int,
        @Query("key") apiKey: String
    ): Game
    //endregion Game
}