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

/**
 * Created by suneet on 12/20/17.
 */
class WaveLoader : LinearLayout, LoaderContract {

    var rectWidth: Int = 50
    var rectHeight: Int = 200

    var rectDistance: Int = 20

    var firstRectColor: Int = resources.getColor(R.color.grey)

    var secondRectColor: Int = resources.getColor(R.color.grey)

    var thirdRectColor: Int = resources.getColor(R.color.grey)

    var animDuration: Int = 500

    var firstDelayDuration: Int = 100
    var secondDelayDuration: Int = 200

    var interpolator: Interpolator = LinearInterpolator()

    private lateinit var firstRect: RectangleView
    private lateinit var secondRect: RectangleView
    private lateinit var thirdRect: RectangleView

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

        val calWidth = (3 * rectWidth) + (2 * rectDistance)
        val calHeight = 2 * rectHeight

        setMeasuredDimension(calWidth, calHeight)
    }

    private fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        firstRect = RectangleView(context, rectWidth, rectHeight, firstRectColor)
        secondRect = RectangleView(context, rectWidth, rectHeight, secondRectColor)
        thirdRect = RectangleView(context, rectWidth, rectHeight, thirdRectColor)

        val firstRectParams = LinearLayout.LayoutParams(rectWidth, rectHeight)
        firstRectParams.topMargin = (rectHeight / 2)
        firstRectParams.bottomMargin = (rectHeight / 2)

        val secondRectParams = LinearLayout.LayoutParams(rectWidth, rectHeight)
        secondRectParams.topMargin = (rectHeight / 2)
        secondRectParams.bottomMargin = (rectHeight / 2)
        secondRectParams.leftMargin = rectDistance

        setVerticalGravity(Gravity.CENTER)

        addView(firstRect, firstRectParams)
        addView(secondRect, secondRectParams)
        addView(thirdRect, secondRectParams)

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

        val rect1ScaleAnim = getTranslateAnim()
        firstRect.startAnimation(rect1ScaleAnim)

        val rect2ScaleAnim = getTranslateAnim()

        Handler().postDelayed({
            secondRect.startAnimation(rect2ScaleAnim)
        }, firstDelayDuration.toLong())


        val rect3ScaleAnim = getTranslateAnim()


        Handler().postDelayed({
            thirdRect.startAnimation(rect3ScaleAnim)
        }, secondDelayDuration.toLong())

        rect1ScaleAnim.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(p0: Animation?) {
                startLoading()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })
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