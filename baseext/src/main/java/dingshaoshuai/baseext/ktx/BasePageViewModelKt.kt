package dingshaoshuai.baseext.ktx

import androidx.lifecycle.viewModelScope
import dingshaoshuai.baseext.mvvm.BasePageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

inline fun <T> BasePageViewModel.launchPageSwitch(
    crossinline blockIO: suspend () -> T?,
    crossinline checkErrorBlock: (T?) -> Boolean,
    crossinline checkEmptyBlock: (T?) -> Boolean,
    crossinline successBlock: suspend (T?) -> Unit,
    crossinline failureBlock: (T?) -> Unit = {},
    crossinline completeBlock: (T?) -> Unit = {}
) {
    viewModelScope.launch(Dispatchers.Main) {
        showLoadingPage()
        val blockIOValue = withContext(Dispatchers.IO) {
            blockIO()
        }
        when {
            checkErrorBlock(blockIOValue) -> {
                showErrorPage()
                failureBlock(blockIOValue)
            }
            checkEmptyBlock(blockIOValue) -> {
                showEmptyPage()
            }
            else -> {
                successBlock(blockIOValue)
                showSuccessPage()
            }
        }
        completeBlock(blockIOValue)
    }
}