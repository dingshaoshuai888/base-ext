package dingshaoshuai.baseext

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback

/**
 * @author: Xiao Bo
 * @date: 18/6/2021
 */
class LoadingCallback : Callback() {
    override fun onCreateView(): Int = R.layout.loading_callback

    override fun onReloadEvent(context: Context?, view: View?) = true
}