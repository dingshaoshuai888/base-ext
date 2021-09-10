package dingshaoshuai.baseext

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import dingshaoshuai.baseext.ktx.launchPageSwitch
import dingshaoshuai.baseext.mvvm.BasePageViewModel
import kotlinx.coroutines.delay

class MainViewModel : BasePageViewModel() {
    private var number = 0

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        initData()
    }

    fun initData() {
        launchPageSwitch(
            blockIO = {
                delay(1000)
                number++
            },
            checkErrorBlock = {
                number < 2
            },
            checkEmptyBlock = {
                number in 2..4
            },
            successBlock = {
                number > 4
            },
            failureBlock = {
                Log.i(TAG, "initData: failureBlock")
            },
            completeBlock = {
                Log.i(TAG, "initData: completeBlock")
            }
        )
    }

    companion object {
        private const val TAG = "MainViewModel"
    }
}