package com.agrawalsuneet.fourfoldloader.basicviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.agrawalsuneet.fourfoldloader.R

/**
 * Created by ballu on 09/04/17.
 */

class SquareView : View, LoaderContract {

    private var mSquareLength: Int = 100
    private var mSquareColor: Int = resources.getColor(R.color.grey)
    private lateinit var squarePaint: Paint

    constructor(context: Context) : super(context) {}

    constructor(context: Context, squareColor: Int, length: Int) : super(context) {
        this.mSquareColor = squareColor
        this.mSquareLength = length
        initValues()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        initAttributes(attrs!!)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initAttributes(attrs!!)
    }

    override fun initAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.SquareView, 0, 0)

        this.mSquareLength = typedArray.getDimension(R.styleable.SquareView_squareLength,
                100f).toInt()
        this.mSquareColor = typedArray.getColor(R.styleable.SquareView_squareColor,
                resources.getColor(R.color.grey))
        typedArray.recycle()
        initValues()
    }

    private fun initValues() {
        squarePaint = Paint()
        squarePaint.color = mSquareColor
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        setMeasuredDimension(mSquareLength, mSquareLength)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawRect(0f, 0f, mSquareLength.toFloat(), mSquareLength.toFloat(), squarePaint!!)
    }
}