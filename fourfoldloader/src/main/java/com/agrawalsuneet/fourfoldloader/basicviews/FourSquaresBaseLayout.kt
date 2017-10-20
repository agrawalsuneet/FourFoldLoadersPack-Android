package com.agrawalsuneet.fourfoldloader.basicviews

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewTreeObserver
import android.view.animation.AccelerateInterpolator
import android.view.animation.Interpolator
import android.widget.LinearLayout
import com.agrawalsuneet.fourfoldloader.R

/**
 * Created by suneet on 10/20/17.
 */
abstract class FourSquaresBaseLayout : LinearLayout, LoaderContract {

    var startLoadingDefault = false

    var animationDuration = 500
    var disappearAnimationDuration = 100

    var interpolator: Interpolator = AccelerateInterpolator()

    //private variables
    protected var noOfSquareVisible = 4
    protected var mainSquare = 1
    protected var isClosing = true

    protected var isLoading: Boolean = false

    protected lateinit var firstSquare: SquareLayout
    protected lateinit var secondSquare: SquareLayout
    protected lateinit var thirdSquare: SquareLayout
    protected lateinit var forthSquare: SquareLayout

    protected lateinit var topLinearLayout: LinearLayout
    protected lateinit var bottomLinearLayout: LinearLayout


    constructor(context: Context) : super(context) {}

    constructor(context: Context, squareColor: Int, length: Int) : super(context) {
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(attrs!!)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs!!)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(2 * squareLenght, 2 * squareLenght)
    }

    override fun initAttributes(attrs: AttributeSet) {
    }


    protected fun initView() {
        removeAllViews()
        removeAllViewsInLayout()

        noOfSquareVisible = 4
        mainSquare = 1

        isLoading = false

        this.orientation = LinearLayout.VERTICAL

        firstSquare = SquareLayout(context, firstSquareColor, squareLenght)
        secondSquare = SquareLayout(context, secondSquareColor, squareLenght)
        thirdSquare = SquareLayout(context, thirdSquareColor, squareLenght)
        forthSquare = SquareLayout(context, forthSquareColor, squareLenght)

        topLinearLayout = LinearLayout(context)
        topLinearLayout.gravity = Gravity.END
        topLinearLayout.orientation = LinearLayout.HORIZONTAL
        topLinearLayout.addView(firstSquare)
        topLinearLayout.addView(secondSquare)

        bottomLinearLayout = LinearLayout(context)
        bottomLinearLayout.gravity = Gravity.END
        bottomLinearLayout.orientation = LinearLayout.HORIZONTAL
        bottomLinearLayout.addView(forthSquare)
        bottomLinearLayout.addView(thirdSquare)

        val llParams = LinearLayout.LayoutParams(2 * squareLenght, squareLenght)
        llParams.gravity = Gravity.END

        addView(topLinearLayout, llParams)
        addView(bottomLinearLayout, llParams)

        requestLayout()

        firstSquare.pivotX = squareLenght.toFloat()
        firstSquare.pivotY = squareLenght.toFloat()

        secondSquare.pivotX = 0f
        secondSquare.pivotY = squareLenght.toFloat()

        thirdSquare.pivotX = 0f
        thirdSquare.pivotY = 0f

        forthSquare.pivotX = squareLenght.toFloat()
        forthSquare.pivotY = 0f

        if (startLoadingDefault) {
            val viewTreeObserver = this.viewTreeObserver
            val loaderView = this

            viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
                override fun onGlobalLayout() {
                    startLoading()

                    val vto = loaderView.viewTreeObserver
                    vto.removeOnGlobalLayoutListener(this)
                }
            })
            startLoadingDefault = false
        }
    }

    abstract fun startLoading()

    var squareLenght = 100
        get() = field
        set(value) {
            field = value
            initView()
        }

    var firstSquareColor = resources.getColor(R.color.red)
        get() = field
        set(value) {
            field = value
            initView()
        }

    var secondSquareColor = resources.getColor(R.color.green)
        get() = field
        set(value) {
            field = value
            initView()
        }

    var thirdSquareColor = resources.getColor(R.color.blue)
        get() = field
        set(value) {
            field = value
            initView()
        }

    var forthSquareColor = resources.getColor(R.color.grey)
        get() = field
        set(value) {
            field = value
            initView()
        }
}