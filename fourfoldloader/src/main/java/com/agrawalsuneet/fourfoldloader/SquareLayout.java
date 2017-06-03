package com.agrawalsuneet.fourfoldloader;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import org.jetbrains.annotations.Nullable;

/**
 * Created by ballu on 09/04/17.
 */

public class SquareLayout extends View {

    private int mSquareLength = 100;
    private int mSquareColor = getResources().getColor(R.color.grey);
    private Paint squarePaint;

    public SquareLayout(Context context) {
        super(context);
    }

    public SquareLayout(Context context, int squareColor, int length) {
        super(context);
        this.mSquareColor = squareColor;
        this.mSquareLength = length;
        initValues();
    }

    public SquareLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(attrs);
    }

    public SquareLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initAttributes(attrs);
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.FourFoldLoader, 0, 0);

        this.mSquareLength = (int) typedArray.getDimension(R.styleable.FourFoldLoader_loader_squareLength,
                100);
        this.mSquareColor = typedArray.getColor(R.styleable.FourFoldLoader_loader_solidColor,
                getResources().getColor(R.color.grey));
        typedArray.recycle();
        initValues();
    }

    private void initValues() {
        squarePaint = new Paint();
        squarePaint.setColor(mSquareColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(mSquareLength, mSquareLength);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(0, 0, mSquareLength, mSquareLength, squarePaint);
    }
}