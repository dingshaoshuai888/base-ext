package dingshaoshuai.baseext

import android.app.Application

/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        PageStatus.init(
            LoadingCallback(),
            EmptyCallback(),
            ErrorCallback(),
            LoadingCallback::class.java
        )
    }
}