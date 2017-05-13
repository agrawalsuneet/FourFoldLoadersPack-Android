package com.agrawalsuneet.fourfoldloader;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

/**
 * Created by ballu on 13/05/17.
 */

public class FourFoldLoader2 extends LinearLayout {

    private Context mContext;
    private int squareLenght;

    private int noOfSquareVisible = 4;
    private int mainSquare = 3;
    private boolean isClosing = true;

    private AnimatorSet mainAnimatorSet = null;
    private Interpolator interpolator = new AccelerateDecelerateInterpolator();

    private LinearLayout topLinearLayout, bottomLinearLayout;
    private LinearLayout.LayoutParams topParams, bottomParams;

    private int animDuration = 5000;

    private boolean isLoading;


    private int firstSquareColor = getResources().getColor(R.color.red),
            secondSquareColor = getResources().getColor(R.color.green),
            thirdSquareColor = getResources().getColor(R.color.blue),
            forthSquareColor = getResources().getColor(R.color.grey);

    private SquareLayout firstSquare, secondSquare, thirdSquare, forthSquare;


    public FourFoldLoader2(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public FourFoldLoader2(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttributes(attrs);
        initView();
    }

    public FourFoldLoader2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initAttributes(attrs);
        initView();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(2 * squareLenght, 2 * squareLenght);
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SquareLoader, 0, 0);

        this.squareLenght = dpToPx(getContext(),
                typedArray.getDimensionPixelSize(R.styleable.SquareLoader_loader_squareLength, 100));

        this.firstSquareColor = typedArray.getColor(R.styleable.SquareLoader_loader_solidColor,
                getResources().getColor(R.color.red));

        this.secondSquareColor = typedArray.getColor(R.styleable.SquareLoader_loader_solidColor,
                getResources().getColor(R.color.green));

        this.thirdSquareColor = typedArray.getColor(R.styleable.SquareLoader_loader_solidColor,
                getResources().getColor(R.color.blue));

        this.forthSquareColor = typedArray.getColor(R.styleable.SquareLoader_loader_solidColor,
                getResources().getColor(R.color.grey));

        typedArray.recycle();
    }


    private void initView() {

        this.setOrientation(VERTICAL);

        firstSquare = new SquareLayout(mContext, firstSquareColor, squareLenght);
        secondSquare = new SquareLayout(mContext, secondSquareColor, squareLenght);
        thirdSquare = new SquareLayout(mContext, thirdSquareColor, squareLenght);
        forthSquare = new SquareLayout(mContext, forthSquareColor, squareLenght);

        topLinearLayout = new LinearLayout(mContext);
        topLinearLayout.setGravity(Gravity.RIGHT);
        topLinearLayout.setOrientation(HORIZONTAL);
        topLinearLayout.addView(firstSquare);
        topLinearLayout.addView(secondSquare);

        bottomLinearLayout = new LinearLayout(mContext);
        bottomLinearLayout.setGravity(Gravity.RIGHT);
        bottomLinearLayout.setOrientation(HORIZONTAL);
        bottomLinearLayout.addView(forthSquare);
        bottomLinearLayout.addView(thirdSquare);

        topParams = new LayoutParams(2 * squareLenght, squareLenght);
        topParams.gravity = Gravity.RIGHT;

        bottomParams = new LayoutParams(2 * squareLenght, squareLenght);
        bottomParams.gravity = Gravity.RIGHT;




        /*topLinearLayout.setLayoutParams(topParams);
        bottomLinearLayout.setLayoutParams(bottomParams);*/

        addView(topLinearLayout, topParams);
        addView(bottomLinearLayout, bottomParams);


        requestLayout();

        firstSquare.setPivotX(squareLenght);
        firstSquare.setPivotY(squareLenght);

        secondSquare.setPivotX(0);
        secondSquare.setPivotY(squareLenght);

        thirdSquare.setPivotX(0);
        thirdSquare.setPivotY(0);

        forthSquare.setPivotX(squareLenght);
        forthSquare.setPivotY(0);
    }

    public void startAnimation() {

        //Test Code

        /*secondSquare.setVisibility(INVISIBLE);
        thirdSquare.setVisibility(INVISIBLE);*/

        /*secondSquare.setVisibility(GONE);
        forthSquare.setVisibility(GONE);*/

        this.getOverlay().add(firstSquare);
        this.getOverlay().add(forthSquare);

        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_close_right);
        mainAnimatorSet.setTarget(firstSquare);
        mainAnimatorSet.setDuration(animDuration);
        mainAnimatorSet.setInterpolator(interpolator);
        mainAnimatorSet.start();

        AnimatorSet thirdSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_close_right);
        thirdSet.setTarget(forthSquare);
        thirdSet.setDuration(animDuration);
        thirdSet.setInterpolator(interpolator);
        thirdSet.start();

        //Test code ends here


    }


    private int dpToPx(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
