package com.agrawalsuneet.fourfoldloader;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ballu on 13/05/17.
 */

public class FourFoldLoader2 extends LinearLayout implements Animator.AnimatorListener {

    private Context mContext;
    private int squareLenght;

    private int noOfSquareVisible = 4;
    private int mainSquare = 1;
    private boolean isClosing = true;

    private AnimatorSet mainAnimatorSet = null;
    private Interpolator interpolator = new AccelerateDecelerateInterpolator();

    private LinearLayout topLinearLayout, bottomLinearLayout;
    private LinearLayout.LayoutParams topParams, bottomParams;

    private int animDuration = 1000;

    private boolean isLoading;

    private List<View> viewsToHide, viewsOverlay;


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

        setMeasuredDimension((2 * squareLenght) + getPaddingLeft() + getPaddingRight(),
                (2 * squareLenght) + getPaddingTop() + getPaddingBottom());
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
        viewsToHide = new ArrayList<>();
        viewsOverlay = new ArrayList<>();

        if (noOfSquareVisible == 4) {
            switch (mainSquare) {
                case 1:
                case 2:
                    this.getOverlay().add(thirdSquare);
                    this.getOverlay().add(forthSquare);

                    viewsOverlay.add(thirdSquare);
                    viewsOverlay.add(forthSquare);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_close_up);
                    mainAnimatorSet.setTarget(thirdSquare);
                    mainAnimatorSet.setDuration(animDuration);
                    mainAnimatorSet.setInterpolator(interpolator);
                    mainAnimatorSet.start();

                    AnimatorSet anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_close_up);
                    anotherSet.setTarget(forthSquare);
                    anotherSet.setDuration(animDuration);
                    anotherSet.setInterpolator(interpolator);
                    anotherSet.start();

                    viewsToHide.add(thirdSquare);
                    viewsToHide.add(forthSquare);

                    break;

                case 3:
                case 4:
                    this.getOverlay().add(firstSquare);
                    this.getOverlay().add(secondSquare);

                    viewsOverlay.add(firstSquare);
                    viewsOverlay.add(secondSquare);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.top_close_down);
                    mainAnimatorSet.setTarget(firstSquare);
                    mainAnimatorSet.setDuration(animDuration);
                    mainAnimatorSet.setInterpolator(interpolator);
                    mainAnimatorSet.start();

                    anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.top_close_down);
                    anotherSet.setTarget(secondSquare);
                    anotherSet.setDuration(animDuration);
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
                        viewsOverlay.add(targetView);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_close_left);
                        mainAnimatorSet.setTarget(targetView);
                        mainAnimatorSet.setDuration(animDuration);
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
                        viewsOverlay.add(targetView);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_close_right);
                        mainAnimatorSet.setTarget(targetView);
                        mainAnimatorSet.setDuration(animDuration);
                        mainAnimatorSet.setInterpolator(interpolator);
                        mainAnimatorSet.start();

                        viewsToHide.add(targetView);

                        break;
                }

                noOfSquareVisible = 1;
            } else if (!isClosing) {
                // fold is opening

                switch (mainSquare) {
                    case 1:
                    case 3:
                        this.getOverlay().add(secondSquare);
                        this.getOverlay().add(thirdSquare);

                        viewsOverlay.add(secondSquare);
                        viewsOverlay.add(thirdSquare);

                        secondSquare.setVisibility(VISIBLE);
                        thirdSquare.setVisibility(VISIBLE);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_open_right);
                        mainAnimatorSet.setTarget(secondSquare);
                        mainAnimatorSet.setDuration(animDuration);
                        mainAnimatorSet.setInterpolator(interpolator);
                        mainAnimatorSet.start();

                        AnimatorSet anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.right_open_right);
                        anotherSet.setTarget(thirdSquare);
                        anotherSet.setDuration(animDuration);
                        anotherSet.setInterpolator(interpolator);
                        anotherSet.start();

                        break;
                    case 2:
                    case 4:
                        this.getOverlay().add(firstSquare);
                        this.getOverlay().add(forthSquare);

                        viewsOverlay.add(firstSquare);
                        viewsOverlay.add(forthSquare);

                        firstSquare.setVisibility(VISIBLE);
                        forthSquare.setVisibility(VISIBLE);

                        mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_open_left);
                        mainAnimatorSet.setTarget(firstSquare);
                        mainAnimatorSet.setDuration(animDuration);
                        mainAnimatorSet.setInterpolator(interpolator);
                        mainAnimatorSet.start();

                        anotherSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.left_open_left);
                        anotherSet.setTarget(forthSquare);
                        anotherSet.setDuration(animDuration);
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
                    viewsOverlay.add(targetView);

                    targetView.setVisibility(VISIBLE);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.bottom_open_down);
                    mainAnimatorSet.setTarget(targetView);
                    mainAnimatorSet.setDuration(animDuration);
                    mainAnimatorSet.setInterpolator(interpolator);
                    mainAnimatorSet.start();

                    mainSquare += 2;
                    break;

                case 3:
                case 4:
                    targetView = (mainSquare == 3 ? secondSquare : firstSquare);

                    this.getOverlay().add(targetView);
                    viewsOverlay.add(targetView);

                    targetView.setVisibility(VISIBLE);

                    mainAnimatorSet = (AnimatorSet) AnimatorInflater.loadAnimator(getContext(), R.animator.top_open_up);
                    mainAnimatorSet.setTarget(targetView);
                    mainAnimatorSet.setDuration(animDuration);
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

        isLoading = false;

        if (viewsToHide != null && viewsToHide.size() > 0) {

            AlphaAnimation alphaAnimation = new AlphaAnimation(R.dimen.to_alpha, 0f);
            alphaAnimation.setDuration(300);
            alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {
                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    for (View view : viewsToHide) {
                        view.setVisibility(INVISIBLE);
                        view.setRotationX(0);
                        view.setRotationY(0);
                    }
                    removeOverLays();
                    startAnimation();
                }

                @Override
                public void onAnimationRepeat(Animation animation) {
                }
            });

            for (View view : viewsToHide) {
                view.startAnimation(alphaAnimation);
            }
        } else {
            startAnimation();
        }
    }

    private void removeOverLays() {
        if (viewsOverlay != null && viewsOverlay.size() > 0) {
            for (View view : viewsOverlay) {
                this.getOverlay().remove(view);
            }
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

    private int dpToPx(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }
}
