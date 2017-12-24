package com.agrawalsuneet.fourfoldloader.loaders

import android.content.Context
import android.os.Handler
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.Animation
import android.view.animation.Interpolator
import android.view.animation.LinearInterpolator
import android.view.animation.ScaleAnimation
import android.widget.LinearLayout
import com.agrawalsuneet.fourfoldloader.R
import com.agrawalsuneet.fourfoldloader.basicviews.LoaderContract
import com.agrawalsuneet.fourfoldloader.basicviews.RectangleView
import java.util.*

/**
 * Created by suneet on 12/20/17.
 */
class WaveLoader : LinearLayout, LoaderContract {

    var noOfRects: Int = 8
        set(value) {
            field = if (value < 2) 2 else value
            invalidate()
        }

    var rectWidth: Int = 50
    var rectHeight: Int = 200

    var rectDistance: Int = 20

    var rectColorsArray: IntArray = IntArray(noOfRects, { resources.getColor(R.color.grey) })

    var animDuration: Int = 500

    var delayDuration: Int = 100

    var interpolator: Interpolator = LinearInterpolator()

    private lateinit var rectArray: ArrayList<RectangleView?>

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initView()
    }

    override fun initAttributes(attrs: AttributeSet) {

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val calWidth = (noOfRects * rectWidth) + ((noOfRects - 1) * rectDistance)
        val calHeight = 2 * rectHeight

        setMeasuredDimension(calWidth, calHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        setVerticalGravity(Gravity.CENTER)

        rectArray = ArrayList(noOfRects)

        for (count in 0 until noOfRects) {
            val rectangleView = RectangleView(context, rectWidth, rectHeight, rectColorsArray[count])

            var rectLayoutParam: LinearLayout.LayoutParams

            when (count) {
                0 -> {
                    rectLayoutParam = LinearLayout.LayoutParams(rectWidth, rectHeight)
                    rectLayoutParam.topMargin = (rectHeight / 2)
                    rectLayoutParam.bottomMargin = (rectHeight / 2)
                }

                else -> {
                    rectLayoutParam = LinearLayout.LayoutParams(rectWidth, rectHeight)
                    rectLayoutParam.topMargin = (rectHeight / 2)
                    rectLayoutParam.bottomMargin = (rectHeight / 2)
                    rectLayoutParam.leftMargin = rectDistance
                }
            }

            this.addView(rectangleView, rectLayoutParam)
            rectArray.add(rectangleView)
        }


        val loaderView = this

        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                startLoading()

                val vto = loaderView.viewTreeObserver
                vto.removeOnGlobalLayoutListener(this)
            }
        })
    }

    fun startLoading() {

        for (count in 0 until noOfRects) {
            val rectScaleAnim = getTranslateAnim()

            val rectView = rectArray.get(count)

            Handler().postDelayed({
                rectView?.startAnimation(rectScaleAnim)
            }, (count * delayDuration).toLong())

            if (count == 0) {
                rectScaleAnim.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationEnd(p0: Animation?) {
                        startLoading()
                    }

                    override fun onAnimationRepeat(p0: Animation?) {
                    }

                    override fun onAnimationStart(p0: Animation?) {
                    }

                })
            }
        }
    }

    private fun getTranslateAnim(): ScaleAnimation {
        val transAnim = ScaleAnimation(1.0f, 1.0f, 0.5f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        transAnim.duration = animDuration.toLong()
        transAnim.fillAfter = true
        transAnim.repeatCount = 1
        transAnim.repeatMode = Animation.REVERSE
        transAnim.interpolator = interpolator

        return transAnim
    }
}