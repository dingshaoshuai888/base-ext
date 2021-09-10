package dingshaoshuai.baseext.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import dingshaoshuai.baseext.R
import dingshaoshuai.baseext.databinding.LoadingPlaceholderviewBinding

/**
 * @author: Xiao Bo
 * @date: 25/8/2021
 */
class LoadingPlaceholderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val binding: LoadingPlaceholderviewBinding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.loading_placeholderview, this, true)

}