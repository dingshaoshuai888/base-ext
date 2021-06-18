package dingshaoshuai.baseext

import android.app.Application
import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadSir

/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
abstract class BaseApp : Application() {

    protected abstract val loadingPageCallback: Callback
    protected abstract val emptyPageCallback: Callback
    protected abstract val errorPageCallback: Callback
    protected abstract val defaultClazz: Class<out Callback>

    override fun onCreate() {
        super.onCreate()
        initLoadSir()
    }

    protected open fun initLoadSir() {
        LoadSir.beginBuilder().apply {
            addCallback(loadingPageCallback)
            addCallback(emptyPageCallback)
            addCallback(errorPageCallback)
            setDefaultCallback(defaultClazz)
        }.commit()

    }
}