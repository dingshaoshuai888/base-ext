package dingshaoshuai.baseext

import com.kingja.loadsir.callback.Callback

/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
class BaseApplication : BaseApp() {
    override val loadingPageCallback: Callback
        get() = LoadingCallback()
    override val emptyPageCallback: Callback
        get() = EmptyCallback()
    override val errorPageCallback: Callback
        get() = ErrorCallback()
    override val defaultClazz: Class<out Callback>
        get() = LoadingCallback::class.java
}