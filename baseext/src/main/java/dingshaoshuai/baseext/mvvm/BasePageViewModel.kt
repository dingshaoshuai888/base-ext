package dingshaoshuai.baseext.mvvm

import dingshaoshuai.base.mvvm.BaseViewModel
import dingshaoshuai.base.mvvm.CallLiveData

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
}