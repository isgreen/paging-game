package br.com.isgreen.study.paginggame.helper.exception

import androidx.annotation.StringRes
import br.com.isgreen.study.paginggame.model.error.ErrorMessage

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

interface ExceptionHandlerHelper {

    fun getErrorMessage(
        exception: Throwable,
        readApiMessage: Boolean? = true,
        @StringRes defaultMessageRes: Int? = null
    ): ErrorMessage

}