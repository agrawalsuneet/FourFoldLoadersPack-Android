package com.agrawalsuneet.fourfoldloader.loaders

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import com.agrawalsuneet.fourfoldloader.R
import com.agrawalsuneet.fourfoldloader.basicviews.FourSquaresBaseLayout
import java.util.*

/**
 * Created by ballu on 13/05/17.
 */

class FourFoldLoader : FourSquaresBaseLayout, Animator.AnimatorListener {

    var overridePadding = false


    private var mainAnimatorSet: AnimatorSet? = null
    private var anotherSet: AnimatorSet? = null
    private var disappearAlphaAnim: AlphaAnimation? = null


    private var viewsToHide: MutableList<View>? = null


    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, startLoadingDefault: Boolean) : super(context) {
        this.startLoadingDefault = startLoadingDefault
        initView()
    }

    constructor(context: Context, squareLenght: Int, firstSquareColor: Int,
                secondSquareColor: Int, thirdSquareColor: Int,
                forthSquareColor: Int, startLoadingDefault: Boolean) : super(context) {
        this.squareLenght = squareLenght
        this.firstSquareColor = firstSquareColor
        this.secondSquareColor = secondSquareColor
        this.thirdSquareColor = thirdSquareColor
        this.forthSquareColor = forthSquareColor
        this.startLoadingDefault = startLoadingDefault
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

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        if (!overridePadding) {
            setPadding(squareLenght / 2, squareLenght / 2,
                    squareLenght / 2, squareLenght / 2)
        }

        setMeasuredDimension(2 * squareLenght + paddingLeft + paddingRight,
                2 * squareLenght + paddingTop + paddingBottom)
    }

    override fun initAttributes(attrs: AttributeSet) {
        super.initAttributes(attrs)

        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FourFoldLoader, 0, 0)

        this.squareLenght = typedArray.getDimensionPixelSize(R.styleable.FourFoldLoader_loader_squareLength, 100)

        this.firstSquareColor = typedArray.getColor(R.styleable.FourFoldLoader_loader_firstSquareColor,
                resources.getColor(R.color.red))

        this.secondSquareColor = typedArray.getColor(R.styleable.FourFoldLoader_loader_secondSquareColor,
                resources.getColor(R.color.green))

        this.thirdSquareColor = typedArray.getColor(R.styleable.FourFoldLoader_loader_thirdSquareColor,
                resources.getColor(R.color.blue))

        this.forthSquareColor = typedArray.getColor(R.styleable.FourFoldLoader_loader_forthSquareColor,
                resources.getColor(R.color.grey))

        this.animationDuration = typedArray.getInteger(R.styleable.FourFoldLoader_loader_animDuration, 500)
        this.disappearAnimationDuration = typedArray.getInteger(R.styleable.FourFoldLoader_loader_disappear_animDuration, 100)

        this.overridePadding = typedArray.getBoolean(R.styleable.FourFoldLoader_loader_overridePadding, false)
        this.startLoadingDefault = typedArray.getBoolean(R.styleable.FourFoldLoader_loader_startLoadingDefault, false)

        typedArray.recycle()
    }


    override fun startLoading() {
        viewsToHide = ArrayList()

        if (noOfSquareVisible == 4) {
            when (mainSquare) {
                1, 2 -> {
                    this.overlay.add(thirdSquare)
                    this.overlay.add(forthSquare)

                    mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.bottom_close_up) as AnimatorSet
                    mainAnimatorSet!!.setTarget(thirdSquare)
                    mainAnimatorSet!!.duration = animationDuration.toLong()
                    mainAnimatorSet!!.interpolator = interpolator
                    mainAnimatorSet!!.start()

                    anotherSet = AnimatorInflater.loadAnimator(context, R.animator.bottom_close_up) as AnimatorSet
                    anotherSet!!.setTarget(forthSquare)
                    anotherSet!!.duration = animationDuration.toLong()
                    anotherSet!!.interpolator = interpolator
                    anotherSet!!.start()

                    viewsToHide!!.add(thirdSquare)
                    viewsToHide!!.add(forthSquare)
                }

                3, 4 -> {
                    this.overlay.add(firstSquare)
                    this.overlay.add(secondSquare)

                    mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.top_close_down) as AnimatorSet
                    mainAnimatorSet!!.setTarget(firstSquare)
                    mainAnimatorSet!!.duration = animationDuration.toLong()
                    mainAnimatorSet!!.interpolator = interpolator
                    mainAnimatorSet!!.start()

                    anotherSet = AnimatorInflater.loadAnimator(context, R.animator.top_close_down) as AnimatorSet
                    anotherSet!!.setTarget(secondSquare)
                    anotherSet!!.duration = animationDuration.toLong()
                    anotherSet!!.interpolator = interpolator
                    anotherSet!!.start()

                    viewsToHide!!.add(firstSquare)
                    viewsToHide!!.add(secondSquare)
                }
            }

            isClosing = true
            noOfSquareVisible = 2

        } else if (noOfSquareVisible == 2) {
            if (isClosing) {
                //fold is closing

                when (mainSquare) {

                    1, 4 -> {
                        //if mainSquare is 1 than 2 should close
                        //or if main square is 4 than 3 should close
                        val targetView: View? = if (mainSquare == 1) secondSquare else thirdSquare
                        topLinearLayout!!.gravity = Gravity.START
                        bottomLinearLayout!!.gravity = Gravity.START

                        this.overlay.add(targetView!!)

                        mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.right_close_left) as AnimatorSet
                        mainAnimatorSet!!.setTarget(targetView)
                        mainAnimatorSet!!.duration = animationDuration.toLong()
                        mainAnimatorSet!!.interpolator = interpolator
                        mainAnimatorSet!!.start()

                        viewsToHide!!.add(targetView)
                    }

                    2, 3 -> {
                        //if mainSquare is 2 than 1 should close
                        //or if main square is 3 than 4 should close
                        val targetView = if (mainSquare == 2) firstSquare else forthSquare

                        topLinearLayout!!.gravity = Gravity.END
                        bottomLinearLayout!!.gravity = Gravity.END

                        this.overlay.add(targetView!!)

                        mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.left_close_right) as AnimatorSet
                        mainAnimatorSet!!.setTarget(targetView)
                        mainAnimatorSet!!.duration = animationDuration.toLong()
                        mainAnimatorSet!!.interpolator = interpolator
                        mainAnimatorSet!!.start()

                        viewsToHide!!.add(targetView)
                    }
                }

                noOfSquareVisible = 1
            } else {
                // fold is opening

                when (mainSquare) {
                    1, 3 -> {
                        this.overlay.add(secondSquare!!)
                        this.overlay.add(thirdSquare!!)

                        secondSquare!!.visibility = View.VISIBLE
                        thirdSquare!!.visibility = View.VISIBLE

                        mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.right_open_right) as AnimatorSet
                        mainAnimatorSet!!.setTarget(secondSquare)
                        mainAnimatorSet!!.duration = animationDuration.toLong()
                        mainAnimatorSet!!.interpolator = interpolator
                        mainAnimatorSet!!.start()

                        anotherSet = AnimatorInflater.loadAnimator(context, R.animator.right_open_right) as AnimatorSet
                        anotherSet!!.setTarget(thirdSquare)
                        anotherSet!!.duration = animationDuration.toLong()
                        anotherSet!!.interpolator = interpolator
                        anotherSet!!.start()
                    }
                    2, 4 -> {
                        this.overlay.add(firstSquare!!)
                        this.overlay.add(forthSquare!!)

                        firstSquare!!.visibility = View.VISIBLE
                        forthSquare!!.visibility = View.VISIBLE

                        mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.left_open_left) as AnimatorSet
                        mainAnimatorSet!!.setTarget(firstSquare)
                        mainAnimatorSet!!.duration = animationDuration.toLong()
                        mainAnimatorSet!!.interpolator = interpolator
                        mainAnimatorSet!!.start()

                        anotherSet = AnimatorInflater.loadAnimator(context, R.animator.left_open_left) as AnimatorSet
                        anotherSet!!.setTarget(forthSquare)
                        anotherSet!!.duration = animationDuration.toLong()
                        anotherSet!!.interpolator = interpolator
                        anotherSet!!.start()
                    }
                }

                noOfSquareVisible = 4
            }

        } else if (noOfSquareVisible == 1) {
            when (mainSquare) {
                1, 2 -> {
                    val targetView: View? = if (mainSquare == 1) forthSquare else thirdSquare

                    this.overlay.add(targetView!!)

                    targetView.visibility = View.VISIBLE

                    mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.bottom_open_down) as AnimatorSet
                    mainAnimatorSet!!.setTarget(targetView)
                    mainAnimatorSet!!.duration = animationDuration.toLong()
                    mainAnimatorSet!!.interpolator = interpolator
                    mainAnimatorSet!!.start()

                    mainSquare += 2
                }

                3, 4 -> {
                    val targetView = if (mainSquare == 3) secondSquare else firstSquare

                    this.overlay.add(targetView!!)

                    targetView!!.setVisibility(View.VISIBLE)

                    mainAnimatorSet = AnimatorInflater.loadAnimator(context, R.animator.top_open_up) as AnimatorSet
                    mainAnimatorSet!!.setTarget(targetView)
                    mainAnimatorSet!!.duration = animationDuration.toLong()
                    mainAnimatorSet!!.interpolator = interpolator
                    mainAnimatorSet!!.start()

                    mainSquare = if (mainSquare == 3) 2 else 1
                }
            }

            noOfSquareVisible = 2
            isClosing = false
        }


        mainAnimatorSet!!.addListener(this)
        isLoading = true
    }

    override fun onAnimationEnd(animation: Animator) {

        if (isLoading) {
            if (viewsToHide != null && viewsToHide!!.size > 0) {

                disappearAlphaAnim = AlphaAnimation(R.dimen.to_alpha.toFloat(), 0f)
                disappearAlphaAnim!!.duration = disappearAnimationDuration.toLong()

                disappearAlphaAnim!!.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationEnd(animation: Animation) {
                        if (isLoading) {
                            for (view in viewsToHide!!) {
                                view.visibility = View.INVISIBLE
                                view.rotationX = 0f
                                view.rotationY = 0f
                            }
                            isLoading = false
                            startLoading()
                        }
                    }

                    override fun onAnimationStart(animation: Animation) {}

                    override fun onAnimationRepeat(animation: Animation) {}
                })

                for (view in viewsToHide!!) {
                    view.startAnimation(disappearAlphaAnim)
                }
            } else {
                isLoading = false
                startLoading()
            }
        }
    }

    fun stopLoading() {

        isLoading = false

        if (mainAnimatorSet != null) {
            mainAnimatorSet!!.cancel()
            mainAnimatorSet!!.removeListener(this)
            mainAnimatorSet = null
        }
        if (anotherSet != null) {
            anotherSet!!.cancel()
            anotherSet = null
        }

        if (disappearAlphaAnim != null) {
            disappearAlphaAnim!!.cancel()
            disappearAlphaAnim!!.setAnimationListener(null)
            disappearAlphaAnim = null
        }

        firstSquare.visibility = View.GONE
        secondSquare.visibility = View.GONE
        thirdSquare.visibility = View.GONE
        forthSquare.visibility = View.GONE
        this.removeView(firstSquare)
        this.removeView(secondSquare)
        this.removeView(thirdSquare)
        this.removeView(forthSquare)


        topLinearLayout.visibility = View.GONE
        bottomLinearLayout.visibility = View.GONE
        this.removeView(topLinearLayout)
        this.removeView(bottomLinearLayout)


        initView()
    }


    override fun onAnimationStart(animation: Animator) {}

    override fun onAnimationCancel(animation: Animator) {}

    override fun onAnimationRepeat(animation: Animator) {}
}
