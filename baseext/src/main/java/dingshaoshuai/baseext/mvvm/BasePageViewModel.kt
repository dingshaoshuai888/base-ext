package dingshaoshuai.baseext.mvvm

import android.util.Log
import androidx.lifecycle.viewModelScope
import dingshaoshuai.base.mvvm.BaseViewModel
import dingshaoshuai.base.mvvm.CallLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
open class BasePageViewModel : BaseViewModel() {
    val pageChangeLiveData = PageChangeLiveData()

    inner class PageChangeLiveData {
        val loadingPageLiveData by lazy {
            CallLiveData()
        }

        val emptyPageLiveData by lazy {
            CallLiveData()
        }

        val errorPageLiveData by lazy {
            CallLiveData()
        }

        val successPageLiveData by lazy {
            CallLiveData()
        }
    }

    fun showLoadingPage() {
        pageChangeLiveData.loadingPageLiveData.call()
    }

    fun showEmptyPage() {
        pageChangeLiveData.emptyPageLiveData.call()
    }

    fun showErrorPage() {
        pageChangeLiveData.errorPageLiveData.call()
    }

    fun showSuccessPage() {
        pageChangeLiveData.successPageLiveData.call()
    }

    protected fun <T> launchOnPageSwitch(
        block: suspend () -> T?,
        // 坑一波，让伙计们知道还是参数 checkErrorBlock = {} 方式好...
        checkErrorBlock: (T?) -> Boolean,
        checkEmptyBlock: (T?) -> Boolean,
        successBlock: (T?) -> Unit
    ) {
        viewModelScope.launch(Dispatchers.Main) {
            showLoadingPage()
            val value = withContext(Dispatchers.IO) {
                try {
                    block()
                } catch (e: Exception) {
                    showErrorPage()
                    Log.e("BasePageViewModel", "launchOnPageSwitch Error: ${e.message}")
                    null
                }
            }
            when {
                checkErrorBlock(value) -> {
                    showErrorPage()
                }
                checkEmptyBlock(value) -> {
                    showEmptyPage()
                }
                else -> {
                    successBlock.invoke(value)
                    showSuccessPage()
                }
            }
        }
    }

}