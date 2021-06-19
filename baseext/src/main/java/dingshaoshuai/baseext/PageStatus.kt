package dingshaoshuai.baseext

import com.kingja.loadsir.callback.Callback
import com.kingja.loadsir.core.LoadSir

/**
 * @author: Xiao Bo
 * @date: 19/6/2021
 */
object PageStatus {

    fun init(
        loadingPageCallback: Callback,
        emptyPageCallback: Callback,
        errorPageCallback: Callback,
        defaultClazz: Class<out Callback>
    ) {
        LoadSir.beginBuilder().apply {
            addCallback(loadingPageCallback)
            addCallback(emptyPageCallback)
            addCallback(errorPageCallback)
            setDefaultCallback(defaultClazz)
        }.commit()
    }
}