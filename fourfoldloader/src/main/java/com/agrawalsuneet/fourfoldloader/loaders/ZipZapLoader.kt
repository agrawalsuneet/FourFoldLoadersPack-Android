package com.agrawalsuneet.fourfoldloader.loaders

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.agrawalsuneet.fourfoldloader.basicviews.FourSquaresBaseLayout

/**
 * Created by suneet on 10/20/17.
 */
open class ZipZapLoader : FourSquaresBaseLayout {

    var fromScale: Float = 1.0f
    var toScale: Float = 0.5f

    private var mainSquare = 1

    private var isScallingDown = true

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

    override fun startLoading() {

        val scaleAnimation = getScaleAnimation()

        /*val scaleAnimation = ScaleAnimation(1.0f, 0.75f, 1.0f, 0.75f)
        scaleAnimation.fillAfter = true
        scaleAnimation.duration = animationDuration.toLong()*/

        var targetView: View = firstSquare

        when (mainSquare) {
            1 -> {
                //firstSquare.startAnimation(scaleAnimation)
                targetView = firstSquare
                mainSquare = 2
            }
            2 -> {
                //firstSquare.startAnimation(scaleAnimation)
                targetView = secondSquare
                mainSquare = 3
            }

            3 -> {
                //firstSquare.startAnimation(scaleAnimation)
                targetView = thirdSquare
                mainSquare = 4
            }
            4 -> {
                //firstSquare.startAnimation(scaleAnimation)
                targetView = forthSquare
                mainSquare = 1
                isScallingDown = !isScallingDown
            }
        }

        //scaleAnimation.start()

        /*val set = AnimationSet(true)
        set.addAnimation(scaleAnimation)*/

        targetView.startAnimation(scaleAnimation)

    }

    private fun getScaleAnimation(): ScaleAnimation {
        var scaleAnim: ScaleAnimation

        when (isScallingDown) {
            true -> {
                /*scaleAnim = ScaleAnimation(R.dimen.zipzap_from_scale.toFloat(), R.dimen.zipzap_to_scale.toFloat(),
                        R.dimen.zipzap_from_scale.toFloat(), R.dimen.zipzap_to_scale.toFloat())*/

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

        scaleAnim.setAnimationListener(object : Animation.AnimationListener {


            override fun onAnimationEnd(p0: Animation?) {
                startLoading()
            }

            override fun onAnimationRepeat(p0: Animation?) {
            }

            override fun onAnimationStart(p0: Animation?) {
            }

        })

        return scaleAnim
    }

}