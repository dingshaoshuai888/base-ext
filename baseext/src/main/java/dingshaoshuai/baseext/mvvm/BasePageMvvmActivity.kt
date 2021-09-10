package dingshaoshuai.baseext.mvvm

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.databinding.ViewStubProxy
import androidx.lifecycle.Observer
import dingshaoshuai.base.mvvm.BaseMvvmActivity
import dingshaoshuai.base.mvvm.BaseMvvmFragment

/**
 * @author: Xiao Bo
 * @date: 2/9/2021
 */
abstract class BasePageMvvmActivity<T : ViewDataBinding, E : BasePageViewModel> :
    BaseMvvmActivity<T, E>() {

    protected abstract val loadingViewStub: ViewStubProxy?
    // 懒的话.. 直接重写这个就完事了...
    protected open var loadingView: View? = null
        set(value) {
            field = value
            onLoadingViewInit(field)
        }
    protected open fun onLoadingViewInit(loadingView: View?) {}

    protected abstract val emptyViewStub: ViewStubProxy?
    protected open var emptyView: View? = null
        set(value) {
            field = value
            onEmptyViewInit(field)
        }
    protected open fun onEmptyViewInit(emptyView: View?) {}

    protected abstract val errorViewStub: ViewStubProxy?
    protected open var errorView: View? = null
        set(value) {
            field = value
            onErrorViewInit(field)
        }
    protected open fun onErrorViewInit(errorView: View?) {}

    protected abstract val successView: View?

    override fun initObserver() {
        super.initObserver()
        viewModel.pageChangeLiveData.let {
            if (loadingViewStub != null || loadingView != null) {
                it.loadingPageLiveData.observe(this) {
                    showLoadingPage()
                }
            }
            if (emptyViewStub != null || emptyView != null) {
                it.emptyPageLiveData.observe(this) {
                    showEmptyPage()
                }
            }
            if (errorViewStub != null || errorView != null) {
                it.errorPageLiveData.observe(this) {
                    showErrorPage()
                }
            }
            if (successView != null) {
                it.successPageLiveData.observe(this) {
                    showSuccessPage()
                }
            }
        }
    }

    protected open fun showLoadingPage() {
        loadingView = loadingView ?: if (loadingViewStub?.isInflated == false) {
            loadingViewStub?.viewStub?.inflate()
        } else {
            null
        }
        loadingView?.visibility = View.VISIBLE

        emptyView?.visibility = View.GONE
        errorView?.visibility = View.GONE
        successView?.visibility = View.GONE
    }

    protected open fun showEmptyPage() {
        emptyView = emptyView ?: if (emptyViewStub?.isInflated == false) {
            emptyViewStub?.viewStub?.inflate()
        } else {
            null
        }
        emptyView?.visibility = View.VISIBLE

        loadingView?.visibility = View.GONE
        errorView?.visibility = View.GONE
        successView?.visibility = View.GONE
    }

    protected open fun showErrorPage() {
        errorView = errorView ?: if (errorViewStub?.isInflated == false) {
            errorViewStub?.viewStub?.inflate()
        } else {
            null
        }
        errorView?.visibility = View.VISIBLE

        loadingView?.visibility = View.GONE
        emptyView?.visibility = View.GONE
        successView?.visibility = View.GONE
    }

    protected open fun showSuccessPage() {
        loadingView?.visibility = View.GONE
        emptyView?.visibility = View.GONE
        errorView?.visibility = View.GONE
        successView?.visibility = View.VISIBLE
    }
}