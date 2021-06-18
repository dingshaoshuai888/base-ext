package dingshaoshuai.baseext

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback

/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
class ErrorCallback : Callback() {
    override fun onCreateView(): Int = R.layout.error_callback

    override fun onReloadEvent(context: Context?, view: View?) = false
}