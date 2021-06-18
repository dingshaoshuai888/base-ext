package dingshaoshuai.baseext

import dingshaoshuai.baseext.mvvm.BasePageViewModel
import kotlinx.coroutines.delay


/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
class MainViewModel : BasePageViewModel() {

    fun launchOnPageSwitchTest() {
        launchOnPageSwitch({
            delay(3000)
            mutableListOf<String>()
        }, {
            it?.isEmpty() ?: false
        }, {
            it == null
        })
    }
}