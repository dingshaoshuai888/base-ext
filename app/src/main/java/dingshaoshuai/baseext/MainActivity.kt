package dingshaoshuai.baseext

import android.view.View
import androidx.databinding.ViewStubProxy
import dingshaoshuai.base.ktx.defaultViewModel
import dingshaoshuai.baseext.databinding.ActivityMainBinding
import dingshaoshuai.baseext.mvvm.BasePageMvvmActivity
import dingshaoshuai.baseext.view.PlaceholderView

class MainActivity : BasePageMvvmActivity<ActivityMainBinding, MainViewModel>() {
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun bindViewModel(viewModel: MainViewModel) {
    }

    override fun initViewModel(): MainViewModel {
        return defaultViewModel()
    }

    override val loadingViewStub: ViewStubProxy
        get() = binding.loadingViewStub
    override val emptyViewStub: ViewStubProxy
        get() = binding.emptyViewStub
    override val errorViewStub: ViewStubProxy
        get() = binding.errorViewStub
    override val successView: View
        get() = binding.tvSuccess

    override fun onErrorViewInit(errorView: View?) {
        super.onErrorViewInit(errorView)
        errorView ?: return
        if (errorView is PlaceholderView) {
            errorView.btnClickListener = View.OnClickListener {
                viewModel.initData()
            }
        }
    }

    override fun onEmptyViewInit(emptyView: View?) {
        super.onEmptyViewInit(emptyView)
        emptyView ?: return
        emptyView.setOnClickListener {
            viewModel.initData()
        }
    }

}