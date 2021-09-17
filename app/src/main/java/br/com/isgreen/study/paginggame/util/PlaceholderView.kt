package br.com.isgreen.study.paginggame.util

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.TypedValue
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.Nullable
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import br.com.isgreen.study.paginggame.R

/**
 * Created by Éverdes Soares on 05/24/2021.
 */

typealias OnRetryClickListener = (() -> Unit)?

class PlaceholderView : ConstraintLayout {

    private var mOnRetryClickListener: OnRetryClickListener = null

    private var mTxtFailed: AppCompatTextView? = null
    private var mBtnRetry: AppCompatButton? = null
    private var mIconFailed: AppCompatImageView? = null

    var onRetryClickListener: OnRetryClickListener = null
        set(value) {
            mOnRetryClickListener = value
            field = value
        }

    constructor (context: Context) : super(context) {
        this.initView(context)
    }

    constructor (context: Context, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        this.initView(context)
    }

    private fun initView(context: Context) {
        visibility = View.GONE

        val inflater = LayoutInflater.from(context).inflate(R.layout.placeholder_view, this)

        mTxtFailed = inflater.findViewById(R.id.txtFailed)
        mIconFailed = inflater.findViewById(R.id.imgFailed)
        mBtnRetry = inflater.findViewById(R.id.btnRetry)

        mBtnRetry?.setOnClickListener {
            if (this.visibility == View.VISIBLE) {
                mOnRetryClickListener?.invoke()
                this.hide()
            }
        }
    }

    fun icon(resId: Int): PlaceholderView {
        this.mIconFailed?.setImageResource(resId)
        return this
    }

    fun text(resId: Int): PlaceholderView {
        this.mTxtFailed?.text = context.getString(resId)
        return this
    }

    fun text(message: String): PlaceholderView {
        this.mTxtFailed?.text = message
        return this
    }

    fun text(message: Any): PlaceholderView {
        if (message is Int) {
            this.mTxtFailed?.text = context.getString(message)
        } else if (message is CharSequence) {
            this.mTxtFailed?.text = message
        }
        return this
    }

    fun showRetry(): PlaceholderView {
        this.mBtnRetry?.isVisible = true
        return this
    }

    fun hideRetry(): PlaceholderView {
        this.mBtnRetry?.isVisible = false
        return this
    }

    fun hideIcon(): PlaceholderView {
        this.mIconFailed?.visibility = View.GONE
        return this
    }

    fun setTextColorRes(@ColorRes color: Int): PlaceholderView {
        return setTextColor(ContextCompat.getColor(context, color))
    }

    fun setTextColor(color: Int): PlaceholderView {
        this.mTxtFailed?.setTextColor(color)
        return this
    }

    fun setTextSize(textSizeSp: Float): PlaceholderView {
        this.mTxtFailed?.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSizeSp)
        return this
    }

    fun setIconColor(@ColorRes color: Int): PlaceholderView {
        this.mIconFailed?.setBackgroundColor(ContextCompat.getColor(context, color))
        return this
    }

    fun setIconColorFilter(colorStateList: ColorStateList?): PlaceholderView {
        mIconFailed?.imageTintList = colorStateList
        return this
    }

    fun setIconHorizontalBias(bias: Float): PlaceholderView {
        val constraintSet = ConstraintSet()
        constraintSet.clone(this)
        constraintSet.setHorizontalBias(R.id.imgFailed, bias)
        constraintSet.applyTo(this)
        return this
    }

    fun setStartTextGravity(): PlaceholderView {
        this.mTxtFailed?.gravity = Gravity.START
        return this
    }

    fun show() {
        clearAnimation()
        visibility = View.VISIBLE
    }

    fun hide() {
        if (this.visibility == View.VISIBLE) {
            clearAnimation()
            this.visibility = View.GONE
        }
    }
}