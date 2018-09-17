package com.guagua.googletrendsvisualize.googletrends

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Message
import android.support.constraint.ConstraintLayout
import com.guagua.googletrendsvisualize.R
import java.util.*


class GoogleTrendsView(context: Context, val trends: Array<String>) : ConstraintLayout(context) {

    private var textView1 = GoogleTrendsTextView(context, OLD_TREND)

    private var textView2 = GoogleTrendsTextView(context, NEW_TREND)

    private val timer: Timer = Timer(true)

    companion object {
        const val MODE_TOP_DOWN = 0
        const val MODE_DOWN_TOP = 1
        const val MODE_LEFT_RIGHT = 2
        const val MODE_RIGHT_LEFT = 3
        const val NEW_TREND = 4
        const val OLD_TREND = 5
    }

    private val handler = @SuppressLint("HandlerLeak")
    object : Handler() {
        override fun handleMessage(msg: Message) {
            super.handleMessage(msg)
            if (msg.what == 0){
                setNewTrendsText()
                setNewTrendsColor()
                startTrendsAnimation()
            }
        }
    }

    private var updateUITask: TimerTask = object : TimerTask() {
        override fun run() {
            val msg = Message()
            msg.what = 0
            handler.sendMessage(msg)
        }
    }

    init {
        addTextViewAndInitPosition()
        initOldTrendsText()
        initOldTrendsColor()
        timer.schedule(updateUITask, getRandomTimeDuration(), getRandomTimeDuration())
    }

    fun addTextViewAndInitPosition(){
        addView(textView1)
        textView1.setXY(0f,0f)
        addView(textView2)
        textView2.setXY((-textView2.width).toFloat(),0f)
    }

    fun initOldTrendsText(){
        textView1.setText(getRandomTrends())
    }

    fun initOldTrendsColor(){
        textView1.setColor(getRandomColor())
    }

    fun setNewTrendsText(){
        getCurrendNewTrend().setText(getRandomTrends())
    }

    fun setNewTrendsColor(){
        getCurrendNewTrend().setColor(getRandomColor())
    }

    fun startTrendsAnimation(){
        val direction = getRandomDirection()
        getCurrendNewTrend().updateNewTrendStartPosition(direction)
        textView1.startAnimation(direction)
        textView2.startAnimation(direction)
    }

    fun getCurrendNewTrend(): GoogleTrendsTextView{
        return if (textView1.STATE == NEW_TREND){
            textView1
        }
        else textView2
    }

    fun getCurrendOldTrend(): GoogleTrendsTextView{
        return if (textView1.STATE == OLD_TREND){
            textView1
        }
        else textView2
    }

    fun getRandomTimeDuration() = (3000L + Math.random()*1000).toLong()

    fun getRandomDirection() = (Math.random()*4).toInt()

    fun getRandomColor(): Int{
        val colors = resources.getIntArray(R.array.google_trends_color_set)
        var i: Int

        do {
            i = (Math.random()*colors.size).toInt()
        }while (i == getCurrendOldTrend().COLOR)

        return i
    }

    fun getRandomTrends(): String{
        val i: Int = (Math.random()*trends.size).toInt()
        return trends[i]
    }


}