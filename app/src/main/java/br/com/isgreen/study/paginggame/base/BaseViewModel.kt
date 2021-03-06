package br.com.isgreen.study.paginggame.base

import androidx.annotation.IdRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.isgreen.study.paginggame.helper.exception.ExceptionHandlerHelper
import br.com.isgreen.study.paginggame.model.error.ErrorMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.net.HttpURLConnection

/**
 * Created by Éverdes Soares on 06/10/2021.
 */

abstract class BaseViewModel(
    private val exceptionHandlerHelper: ExceptionHandlerHelper
) : ViewModel(), BaseContract.ViewModel {

    override val loading: LiveData<Boolean>
        get() = mLoadingChanged
    override val message: LiveData<String>
        get() = mMessage
    val redirect: LiveData<Int>
        get() = mRedirect

    protected val mMessage = MutableLiveData<String>()
    protected val mRedirect = MutableLiveData<@IdRes Int>()
    protected val mLoadingChanged = MutableLiveData<Boolean>()

    protected fun <T> LiveData<T>.postValue(data: T) {
        if (this is MutableLiveData<T>) {
            postValue(data)
        }
    }

    protected fun LiveData<Unit>.call() {
        postValue(Unit)
    }

    protected fun defaultLaunch(
        validatorHelper: BaseValidatorHelper? = null,
        vararg anys: Any?,
        block: suspend CoroutineScope.() -> Unit
    ) {
        viewModelScope.launch {
            try {
                mLoadingChanged.postValue(true)

                validatorHelper?.let {
                    val validation = validatorHelper.validate(*anys)
                    if (!validation.isNullOrEmpty()) {
                        mLoadingChanged.postValue(false)
                        mMessage.postValue(validation)
                        return@launch
                    }
                }

                block.invoke(this)
            } catch (exception: Exception) {
                handleException(exception)
            } finally {
                mLoadingChanged.postValue(false)
            }
        }
    }

    protected fun handleException(exception: Exception) {
        val errorMessage = exceptionHandlerHelper.getErrorMessage(exception)
        callAction(errorMessage)
    }

    private fun callAction(errorMessage: ErrorMessage) {
        // to force user to login
        if (errorMessage.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
//            mRedirect.postValue(R.id.splashFragment)
        } else {
            mMessage.postValue(errorMessage.message)
        }
    }
}