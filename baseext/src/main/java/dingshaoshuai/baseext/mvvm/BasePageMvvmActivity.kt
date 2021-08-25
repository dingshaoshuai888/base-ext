package dingshaoshuai.baseext.mvvm

import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
import dingshaoshuai.base.mvvm.BaseMvvmActivity

/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
abstract class BasePageMvvmActivity<T : ViewDataBinding, E : BasePageViewModel> :
    BaseMvvmActivity<T, E>() {

    private var loadService: LoadService<*>? = null
    protected abstract val placeholderView: View
    protected abstract val loadingPageCallbackClazz: Class<out Callback>
    protected abstract val emptyPageCallbackClazz: Class<out Callback>
    protected abstract val errorPageCallbackClazz: Class<out Callback>

    override fun initCustom() {
        super.initCustom()
        loadService = LoadSir.getDefault().register(placeholderView) {
            showLoadingPage()
            initData()
        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.pageChangeLiveData.let {
            it.loadingPageLiveData.observe(this, Observer {
                showLoadingPage()
            })
            it.emptyPageLiveData.observe(this, Observer {
                showEmptyPage()
            })
            it.errorPageLiveData.observe(this, Observer {
                showErrorPage()
            })
            it.successPageLiveData.observe(this, Observer {
                showSuccessPage()
            })
        }
    }

    protected open fun showLoadingPage() {
        loadService?.showCallback(loadingPageCallbackClazz)
    }

    protected open fun showEmptyPage() {
        loadService?.showCallback(emptyPageCallbackClazz)
    }

    protected open fun showErrorPage() {
        loadService?.showCallback(errorPageCallbackClazz)
    }

    protected open fun showSuccessPage() {
        loadService?.showSuccess()
    }
}