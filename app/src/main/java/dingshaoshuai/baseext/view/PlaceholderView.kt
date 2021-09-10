package dingshaoshuai.baseext.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import dingshaoshuai.baseext.R
import dingshaoshuai.baseext.databinding.PlaceholderviewBinding

/**
 * @author: Xiao Bo
 * @date: 25/8/2021
 */
class PlaceholderView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    var btnClickListener: View.OnClickListener? = null

    private val binding: PlaceholderviewBinding = DataBindingUtil
        .inflate(LayoutInflater.from(context), R.layout.placeholderview, this, true)

    init {
        val array = context.obtainStyledAttributes(attrs, R.styleable.PlaceholderView)
        try {
            val text = array.getString(R.styleable.PlaceholderView_PlaceholderView_text)
            val btnText = array.getString(R.styleable.PlaceholderView_PlaceholderView_btnText)
            val drawable = array.getDrawable(R.styleable.PlaceholderView_PlaceholderView_img)
            val displayButton =
                array.getBoolean(R.styleable.PlaceholderView_PlaceholderView_btnDisplay, true)
            binding.iv.setImageDrawable(drawable)
            binding.tv.text = text
            binding.btn.text = btnText
            binding.btn.visibility = if (displayButton) View.VISIBLE else View.GONE
            binding.btn.setOnClickListener {
                btnClickListener?.onClick(it)
            }
        } finally {
            array.recycle()
        }
    }
}