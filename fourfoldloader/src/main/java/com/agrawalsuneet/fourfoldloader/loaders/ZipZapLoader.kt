package com.agrawalsuneet.fourfoldloader.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.agrawalsuneet.fourfoldloader.R
import com.agrawalsuneet.fourfoldloader.basicviews.FourSquaresBaseLayout

/**
 * Created by suneet on 10/20/17.
 */
open class ZipZapLoader : FourSquaresBaseLayout {

    var fromScale: Float = 1.0f
        set(value) {
            field = value
            setInitialScale()
        }

    var toScale: Float = 0.8f

    private var mainSquare = 1

    private var isScallingDown = true

    private var scaleAnim: ScaleAnimation? = null

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, squareLenght: Int, firstSquareColor: Int,
                secondSquareColor: Int, thirdSquareColor: Int,
                forthSquareColor: Int, startLoadingDefault: Boolean)
            : super(context, squareLenght, firstSquareColor,
            secondSquareColor, thirdSquareColor,
            forthSquareColor, startLoadingDefault) {
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        initAttributes(attrs)
        initView()
    }

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs)
        initView()
    }

    override fun initView() {
        super.initView()

        mainSquare = 1
        setInitialScale()
    }

    private fun setInitialScale() {
        if (fromScale != 0.0f && fromScale != 1.0f && firstSquare != null ) {
            firstSquare!!.scaleX = fromScale
            firstSquare!!.scaleY = fromScale

            secondSquare.scaleX = fromScale
            secondSquare.scaleY = fromScale

            thirdSquare.scaleX = fromScale
            thirdSquare.scaleY = fromScale

            forthSquare.scaleX = fromScale
            forthSquare.scaleY = fromScale
        }
    }

    override fun initAttributes(attrs: AttributeSet) {
        super.initAttributes(attrs)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.ZipZapLoader, 0, 0)

        fromScale = typedArray.getFloat(R.styleable.ZipZapLoader_zipzap_fromScale, 1.0f)

        toScale = typedArray.getFloat(R.styleable.ZipZapLoader_zipzap_toScale, 0.8f)

        typedArray.recycle()
    }

    override fun startLoading() {

        val scaleAnimation = getScaleAnimation()

        var targetView: View = firstSquare!!

        when (mainSquare) {
            1 -> {
                targetView = firstSquare!!
                mainSquare = 2
            }
            2 -> {
                targetView = secondSquare
                mainSquare = 3
            }

            3 -> {
                targetView = thirdSquare
                mainSquare = 4
            }
            4 -> {
                targetView = forthSquare
                mainSquare = 1
                isScallingDown = !isScallingDown
            }
        }

        targetView.startAnimation(scaleAnimation)
        isLoading = true
    }

    private fun getScaleAnimation(): ScaleAnimation {
        val scaleAnim: ScaleAnimation

        when (isScallingDown) {
            true -> {
                scaleAnim = ScaleAnimation(fromScale, toScale, fromScale, toScale,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            }

            false -> {
                scaleAnim = ScaleAnimation(toScale, fromScale, toScale, fromScale,
                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            }

        }

        scaleAnim.fillAfter = true
        scaleAnim.duration = animationDuration.toLong()
        scaleAnim.interpolator = interpolator

        scaleAnim.setAnimationListener(object : Animation.AnimationListener {


            override fun onAnimationEnd(p0: Animation?) {
                if (isLoading) {
                    startLoading()
                }
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })

        return scaleAnim
    }

    override fun stopLoading() {

        isLoading = false

        if (scaleAnim != null) {
            scaleAnim!!.cancel()
            scaleAnim!!.setAnimationListener(null)
            scaleAnim = null
        }

        this.removeView(firstSquare)
        this.removeView(secondSquare)
        this.removeView(thirdSquare)
        this.removeView(forthSquare)

        this.removeView(topLinearLayout)
        this.removeView(bottomLinearLayout)


        initView()
    }

}