package com.agrawalsuneet.fourfoldloader;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ballu on 13/05/17.
 */

public class FourFoldLoader extends LinearLayout implements Animator.AnimatorListener {

    //public variables
    private boolean isLoading;

    private int squareLenght;

    private int firstSquareColor = getResources().getColor(R.color.red);
    private int secondSquareColor = getResources().getColor(R.color.green);
    private int thirdSquareColor = getResources().getColor(R.color.blue);
    private int forthSquareColor = getResources().getColor(R.color.grey);

    private int animDur = 500,
            disappearAnimDur = 100;

    private Interpolator interpolator = new AccelerateInterpolator();

    //private variables
    private Context mContext;
    private int noOfSquareVisible = 4;
    private int mainSquare = 1;
    private boolean isClosing = true;

    private AnimatorSet mainAnimatorSet, anotherSet;
    private AlphaAnimation disappearAlphaAnim;

    private LinearLayout topLinearLayout, bottomLinearLayout;
    private LinearLayout.LayoutParams topParams, bottomParams;

    private List<View> viewsToHide;

    private SquareLayout firstSquare, secondSquare, thirdSquare, forthSquare;

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

        setMeasuredDimension((2 * squareLenght) + getPaddingLeft() + getPaddingRight(),
                (2 * squareLenght) + getPaddingTop() + getPaddingBottom());
    }

    private void initAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(attrs, R.styleable.SquareLoader, 0, 0);

        this.squareLenght = typedArray.getDimensionPixelSize(R.styleable.SquareLoader_loader_squareLength, 100);

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
        removeAllViews();
        removeAllViewsInLayout();

        noOfSquareVisible = 4;
        mainSquare = 1;

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

    public void startLoading() {
        viewsToHide = new ArrayList<>();

        if (noOfSquareVisible == 4) {
            switch (mainSquare) {
                case 1:
                case 2:
                    this.getOverlay().add(thirdSquare);
                    this.getOverlay().add(forthSquare);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_close_up);
                    mainAnimatorSet.setTarget(thirdSquare);
                    mainAnimatorSet.setDuration(animDur);
                    mainAnimatorSet.setInterpolator(interpolator);
                    mainAnimatorSet.start();

                    anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_close_up);
                    anotherSet.setTarget(forthSquare);
                    anotherSet.setDuration(animDur);
                    anotherSet.setInterpolator(interpolator);
                    anotherSet.start();

                    viewsToHide.add(thirdSquare);
                    viewsToHide.add(forthSquare);

                    break;

                case 3:
                case 4:
                    this.getOverlay().add(firstSquare);
                    this.getOverlay().add(secondSquare);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.top_close_down);
                    mainAnimatorSet.setTarget(firstSquare);
                    mainAnimatorSet.setDuration(animDur);
                    mainAnimatorSet.setInterpolator(interpolator);
                    mainAnimatorSet.start();

                    anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.top_close_down);
                    anotherSet.setTarget(secondSquare);
                    anotherSet.setDuration(animDur);
                    anotherSet.setInterpolator(interpolator);
                    anotherSet.start();

                    viewsToHide.add(firstSquare);
                    viewsToHide.add(secondSquare);

                    break;
            }

            isClosing = true;
            noOfSquareVisible = 2;

        } else if (noOfSquareVisible == 2) {
            if (isClosing) {
                //fold is closing

                switch (mainSquare) {

                    case 1:
                    case 4:
                        //if mainSquare is 1 than 2 should close
                        //or if main square is 4 than 3 should close
                        View targetView = (mainSquare == 1 ? secondSquare : thirdSquare);
                        topLinearLayout.setGravity(Gravity.LEFT);
                        bottomLinearLayout.setGravity(Gravity.LEFT);

                        this.getOverlay().add(targetView);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_close_left);
                        mainAnimatorSet.setTarget(targetView);
                        mainAnimatorSet.setDuration(animDur);
                        mainAnimatorSet.setInterpolator(interpolator);
                        mainAnimatorSet.start();

                        viewsToHide.add(targetView);

                        break;

                    case 2:
                    case 3:
                        //if mainSquare is 2 than 1 should close
                        //or if main square is 3 than 4 should close
                        targetView = (mainSquare == 2 ? firstSquare : forthSquare);

                        topLinearLayout.setGravity(Gravity.RIGHT);
                        bottomLinearLayout.setGravity(Gravity.RIGHT);

                        this.getOverlay().add(targetView);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_close_right);
                        mainAnimatorSet.setTarget(targetView);
                        mainAnimatorSet.setDuration(animDur);
                        mainAnimatorSet.setInterpolator(interpolator);
                        mainAnimatorSet.start();

                        viewsToHide.add(targetView);

                        break;
                }

                noOfSquareVisible = 1;
            } else {
                // fold is opening

                switch (mainSquare) {
                    case 1:
                    case 3:
                        this.getOverlay().add(secondSquare);
                        this.getOverlay().add(thirdSquare);

                        secondSquare.setVisibility(VISIBLE);
                        thirdSquare.setVisibility(VISIBLE);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_open_right);
                        mainAnimatorSet.setTarget(secondSquare);
                        mainAnimatorSet.setDuration(animDur);
                        mainAnimatorSet.setInterpolator(interpolator);
                        mainAnimatorSet.start();

                        anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_open_right);
                        anotherSet.setTarget(thirdSquare);
                        anotherSet.setDuration(animDur);
                        anotherSet.setInterpolator(interpolator);
                        anotherSet.start();

                        break;
                    case 2:
                    case 4:
                        this.getOverlay().add(firstSquare);
                        this.getOverlay().add(forthSquare);

                        firstSquare.setVisibility(VISIBLE);
                        forthSquare.setVisibility(VISIBLE);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_open_left);
                        mainAnimatorSet.setTarget(firstSquare);
                        mainAnimatorSet.setDuration(animDur);
                        mainAnimatorSet.setInterpolator(interpolator);
                        mainAnimatorSet.start();

                        anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_open_left);
                        anotherSet.setTarget(forthSquare);
                        anotherSet.setDuration(animDur);
                        anotherSet.setInterpolator(interpolator);
                        anotherSet.start();

                        break;

                }

                noOfSquareVisible = 4;
            }

        } else if (noOfSquareVisible == 1) {
            switch (mainSquare) {
                case 1:
                case 2:
                    View targetView = (mainSquare == 1 ? forthSquare : thirdSquare);

                    this.getOverlay().add(targetView);

                    targetView.setVisibility(VISIBLE);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_open_down);
                    mainAnimatorSet.setTarget(targetView);
                    mainAnimatorSet.setDuration(animDur);
                    mainAnimatorSet.setInterpolator(interpolator);
                    mainAnimatorSet.start();

                    mainSquare += 2;
                    break;

                case 3:
                case 4:
                    targetView = (mainSquare == 3 ? secondSquare : firstSquare);

                    this.getOverlay().add(targetView);

                    targetView.setVisibility(VISIBLE);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.top_open_up);
                    mainAnimatorSet.setTarget(targetView);
                    mainAnimatorSet.setDuration(animDur);
                    mainAnimatorSet.setInterpolator(interpolator);
                    mainAnimatorSet.start();

                    mainSquare = (mainSquare == 3 ? 2 : 1);
                    break;
            }

            noOfSquareVisible = 2;
            isClosing = false;
        }


        mainAnimatorSet.addListener(this);
        isLoading = true;

    }

    @Override
    public void onAnimationEnd(Animator animation) {

        if (isLoading) {
            if (viewsToHide != null && viewsToHide.size() > 0) {

                disappearAlphaAnim = new AlphaAnimation(R.dimen.to_alpha, 0f);
                disappearAlphaAnim.setDuration(disappearAnimDur);
                disappearAlphaAnim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationEnd(Animation animation) {
                        if (isLoading) {
                            for (View view : viewsToHide) {
                                view.setVisibility(INVISIBLE);
                                view.setRotationX(0);
                                view.setRotationY(0);
                            }
                            isLoading = false;
                            startLoading();
                        }
                    }

                    @Override
                    public void onAnimationStart(Animation animation) {
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });

                for (View view : viewsToHide) {
                    view.startAnimation(disappearAlphaAnim);
                }
            } else {
                isLoading = false;
                startLoading();
            }
        }
    }

    public void stopLoading() {

        isLoading = false;

        if (mainAnimatorSet != null) {
            mainAnimatorSet.cancel();
            mainAnimatorSet.removeListener(this);
            mainAnimatorSet = null;
        }
        if (anotherSet != null) {
            anotherSet.cancel();
            anotherSet = null;
        }

        if (disappearAlphaAnim != null) {
            disappearAlphaAnim.cancel();
            disappearAlphaAnim.setAnimationListener(null);
            disappearAlphaAnim = null;
        }

        this.removeView(firstSquare);
        this.removeView(secondSquare);
        this.removeView(thirdSquare);
        this.removeView(forthSquare);
        firstSquare.setVisibility(GONE);
        secondSquare.setVisibility(GONE);
        thirdSquare.setVisibility(GONE);
        forthSquare.setVisibility(GONE);

        this.removeView(topLinearLayout);
        this.removeView(bottomLinearLayout);
        topLinearLayout.setVisibility(GONE);
        bottomLinearLayout.setVisibility(GONE);

        initView();
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

    public Interpolator getInterpolator() {
        return interpolator;
    }

    public void setInterpolator(Interpolator interpolator) {
        this.interpolator = interpolator;
    }

    public int getSquareLenght() {
        return squareLenght;
    }

    public void setSquareLenght(int squareLenght) {
        this.squareLenght = squareLenght;
        initView();
    }

    public int getFirstSquareColor() {
        return firstSquareColor;
    }

    public void setFirstSquareColor(int firstSquareColor) {
        this.firstSquareColor = firstSquareColor;
        initView();
    }

    public int getSecondSquareColor() {
        return secondSquareColor;
    }

    public void setSecondSquareColor(int secondSquareColor) {
        this.secondSquareColor = secondSquareColor;
        initView();
    }

    public int getThirdSquareColor() {
        return thirdSquareColor;
    }

    public void setThirdSquareColor(int thirdSquareColor) {
        this.thirdSquareColor = thirdSquareColor;
        initView();
    }

    public int getForthSquareColor() {
        return forthSquareColor;
    }

    public void setForthSquareColor(int forthSquareColor) {
        this.forthSquareColor = forthSquareColor;
        initView();
    }

    public int getAnimationDuration() {
        return animDur;
    }

    public void setAnimationDuration(int animDur) {
        this.animDur = animDur;
    }

    public int getDisappearAnimationDuration() {
        return disappearAnimDur;
    }

    public void setDisappearAnimationDurationr(int disappearAnimDur) {
        this.disappearAnimDur = disappearAnimDur;
    }

    public boolean isLoading() {
        return isLoading;
    }
}
