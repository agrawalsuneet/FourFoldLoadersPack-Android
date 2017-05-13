package com.agrawalsuneet.fourfoldloader;


import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ballu on 09/04/17.
 */

public class FourFoldLoader extends RelativeLayout implements Animator.AnimatorListener {

    private Context mContext;
    private int squareLenght;

    private int noOfSquareVisible = 4;
    private int mainSquare = 1;
    private boolean isClosing = true;

    private AnimatorSet mainAnimatorSet = null;
    private Interpolator interpolator = new AccelerateDecelerateInterpolator();


    private int firstSquareColor = getResources().getColor(R.color.red),
            secondSquareColor = getResources().getColor(R.color.green),
            thirdSquareColor = getResources().getColor(R.color.blue),
            forthSquareColor = getResources().getColor(R.color.grey);

    private SquareLayout firstSquare, secondSquare, thirdSquare, forthSquare;

    private List<View> viewsToHide;

    private int animDuration = 1000;

    private boolean isLoading;

    public FourFoldLoader(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public FourFoldLoader(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initAttributes(attrs);
        initView();
    }

    public FourFoldLoader(Context context, AttributeSet attrs, int defStyleAttr) {
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
        firstSquare = new SquareLayout(mContext, firstSquareColor, squareLenght);
        secondSquare = new SquareLayout(mContext, secondSquareColor, squareLenght);
        thirdSquare = new SquareLayout(mContext, thirdSquareColor, squareLenght);
        forthSquare = new SquareLayout(mContext, forthSquareColor, squareLenght);

        RelativeLayout.LayoutParams firstParam = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        firstParam.addRule(ALIGN_PARENT_START, RelativeLayout.TRUE);
        firstSquare.setId(R.id.firstSquareId);
        firstSquare.setLayoutParams(firstParam);

        RelativeLayout.LayoutParams secondParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        secondParams.addRule(RelativeLayout.RIGHT_OF, firstSquare.getId());
        secondSquare.setId(R.id.secondSquareId);
        secondSquare.setLayoutParams(secondParams);

        RelativeLayout.LayoutParams forthParams = new RelativeLayout.LayoutParams(squareLenght, squareLenght);
        forthParams.addRule(RelativeLayout.BELOW, firstSquare.getId());
        forthSquare.setId(R.id.forthSquareId);
        forthSquare.setLayoutParams(forthParams);

        RelativeLayout.LayoutParams thirdParams = new RelativeLayout.LayoutParams(squareLenght, squareLenght);
        thirdParams.addRule(RelativeLayout.RIGHT_OF, forthSquare.getId());
        thirdParams.addRule(RelativeLayout.BELOW, secondSquare.getId());
        thirdSquare.setId(R.id.thirdSquareId);
        thirdSquare.setLayoutParams(thirdParams);

        addView(firstSquare);
        addView(secondSquare);
        addView(forthSquare);
        addView(thirdSquare);

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

        viewsToHide = new ArrayList<>();

        if (noOfSquareVisible == 4) {
            mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_close_up);
            mainAnimatorSet.setTarget(forthSquare);
            mainAnimatorSet.setDuration(animDuration);
            mainAnimatorSet.setInterpolator(interpolator);
            mainAnimatorSet.start();

            AnimatorSet thirdSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_close_up);
            thirdSet.setTarget(thirdSquare);
            thirdSet.setDuration(animDuration);
            thirdSet.setInterpolator(interpolator);
            thirdSet.start();

            viewsToHide.add(forthSquare);
            viewsToHide.add(thirdSquare);

            isClosing = true;
            noOfSquareVisible = 2;
        } else if (noOfSquareVisible == 2) {
            if (isClosing) {
                mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_close_left);
                mainAnimatorSet.setTarget(secondSquare);
                mainAnimatorSet.setDuration(animDuration);
                mainAnimatorSet.setInterpolator(interpolator);
                mainAnimatorSet.start();

                viewsToHide.add(secondSquare);
                noOfSquareVisible = 1;
            } else {
                forthSquare.setVisibility(VISIBLE);
                thirdSquare.setVisibility(VISIBLE);

                mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_open_down);
                mainAnimatorSet.setTarget(forthSquare);
                mainAnimatorSet.setDuration(animDuration);
                mainAnimatorSet.setInterpolator(interpolator);
                mainAnimatorSet.start();

                AnimatorSet thirdSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_open_down);
                thirdSet.setTarget(thirdSquare);
                thirdSet.setDuration(animDuration);
                thirdSet.setInterpolator(interpolator);
                thirdSet.start();

                noOfSquareVisible = 4;
            }

        } else if (noOfSquareVisible == 1) {
            secondSquare.setVisibility(VISIBLE);

            mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_open_right);
            mainAnimatorSet.setTarget(secondSquare);
            mainAnimatorSet.setDuration(animDuration);
            mainAnimatorSet.setInterpolator(interpolator);
            mainAnimatorSet.start();

            noOfSquareVisible = 2;
            isClosing = false;
        }

        mainAnimatorSet.addListener(this);
        isLoading = true;
    }

    public void stopAnimation() {
        if (mainAnimatorSet != null) {
            mainAnimatorSet.removeListener(this);
        }
        removeAllViews();
        initView();
        isLoading = false;
        noOfSquareVisible = 4;
    }

    private int dpToPx(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }


    public boolean isLoading() {
        return isLoading;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        isLoading = false;

        if (viewsToHide != null && viewsToHide.size() > 0){

            AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 0f);
            alphaAnimation.setDuration(300);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    for (View view : viewsToHide){
                        view.setVisibility(GONE);
                    }
                    startAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }
            });

            for (View view : viewsToHide){
                view.startAnimation(alphaAnimation);
                //view.setVisibility(GONE);
            }
        } else {
            startAnimation();
        }


    }

    @Override
    public void onAnimationStart(Animator animation) {
    }

    @Override
    public void onAnimationCancel(Animator animation) {
    }

    @Override
    public void onAnimationRepeat(Animator animation) {
    }
}
