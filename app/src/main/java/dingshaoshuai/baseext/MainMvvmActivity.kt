package dingshaoshuai.baseext

import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.kingja.loadsir.callback.Callback
import dingshaoshuai.baseext.databinding.ActivityMainBinding
import dingshaoshuai.baseext.mvvm.BasePageMvvmActivity

class MainMvvmActivity : BasePageMvvmActivity<ActivityMainBinding, MainViewModel>() {
    override val placeholderView: View
        get() = binding.content
    override val loadingPageCallbackClazz: Class<out Callback>
        get() = LoadingCallback::class.java
    override val emptyPageCallbackClazz: Class<out Callback>
        get() = EmptyCallback::class.java
    override val errorPageCallbackClazz: Class<out Callback>
        get() = ErrorCallback::class.java
    override val layoutId: Int
        get() = R.layout.activity_main

    override fun bindViewModel(viewModel: MainViewModel) {
        binding.viewModel = viewModel
    }

    override fun initViewModel(): MainViewModel {
        return ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)
    }

    override fun initData() {
        super.initData()
        viewModel.launchOnPageSwitchTest()
    }
}