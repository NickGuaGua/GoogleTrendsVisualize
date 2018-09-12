package com.guagua.googletrendsvisualize.googletrends

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.os.Handler
import android.view.View
import android.widget.EditText
import android.widget.RelativeLayout
import com.guagua.googletrendsvisualize.R

class GoogleTrendsTextView(context: Context): RelativeLayout(context){

    var text: EditText

    var STATE: Int = 0
    var COLOR: Int = -1

    var trends: String = ""
    var textIndex = 0

    private var mHandler: Handler
    private lateinit var typeTextEffect: Runnable

    constructor(context: Context, state: Int): this(context){
        this.STATE = state
    }

    init {
        View.inflate(context, R.layout.google_trends_view, this)
        text = this.findViewById(R.id.text)
        mHandler = Handler()
        typeTextEffect = Runnable {
            this.text.setText(trends.subSequence(0, textIndex))
            this.text.setSelection(textIndex++)
            if (textIndex <= trends.length){
                mHandler.postDelayed(typeTextEffect, 100)
            }
        }
    }

    fun updateNewTrendStartPosition(mode: Int){
        when(mode){
            GoogleTrendsView.MODE_TOP_DOWN -> setXY(0f, (-height).toFloat())
            GoogleTrendsView.MODE_DOWN_TOP -> setXY(0f, (height).toFloat())
            GoogleTrendsView.MODE_LEFT_RIGHT -> setXY((-width).toFloat(), 0f)
            GoogleTrendsView.MODE_RIGHT_LEFT -> setXY((width).toFloat(), 0f)
        }
    }

    fun startAnimation(mode: Int){
        startMoveAnimation(mode)
        startFadeIn()
    }

    fun setXY(x: Float, y: Float){
        this.x = x
        this.y = y
    }

    fun setColor(index: Int){
        COLOR = index
        val colors = resources.getIntArray(R.array.google_trends_color_set)
        setBackgroundColor(colors[index])
    }

    fun setText(trends: String){
        text.setText("")
        this.trends = trends
        textIndex = 0
        mHandler.removeCallbacks(typeTextEffect)
        mHandler.postDelayed(typeTextEffect, 500)
    }

    private fun changeState(){
        if (STATE == GoogleTrendsView.NEW_TREND)
            STATE = GoogleTrendsView.OLD_TREND
        else
            STATE = GoogleTrendsView.NEW_TREND
    }

    private fun startMoveAnimation(mode: Int){
        var animator = setAnimatorDirection(mode)
        animator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {}
            override fun onAnimationCancel(animation: Animator?) {}
            override fun onAnimationStart(animation: Animator?) {}
            override fun onAnimationEnd(animation: Animator?) {
                changeState()
            }
        })
        animator.duration = 600
        animator.start()
    }

    private fun startFadeIn(){
        if (STATE == GoogleTrendsView.NEW_TREND) {
            val animator = ObjectAnimator.ofFloat(text, "alpha", 0.1f, 1f)
            animator.duration = 1600
            animator.start()
        }
    }

    private fun setAnimatorDirection(mode: Int): ObjectAnimator{
        when(mode){
            GoogleTrendsView.MODE_TOP_DOWN -> return ObjectAnimator.ofFloat(this, "translationY", this.y, this.y + this.height)
            GoogleTrendsView.MODE_DOWN_TOP -> return ObjectAnimator.ofFloat(this, "translationY", this.y, this.y - this.height)
            GoogleTrendsView.MODE_LEFT_RIGHT -> return ObjectAnimator.ofFloat(this, "translationX", this.x, this.x + this.width)
            GoogleTrendsView.MODE_RIGHT_LEFT -> return ObjectAnimator.ofFloat(this, "translationX", this.x, this.x - this.width)
        }
        return ObjectAnimator()
    }

}