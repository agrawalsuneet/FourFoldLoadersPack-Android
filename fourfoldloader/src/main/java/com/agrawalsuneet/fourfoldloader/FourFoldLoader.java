package com.agrawalsuneet.fourfoldloader;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by ballu on 09/04/17.
 */

public class FourFoldLoader extends RelativeLayout {

    private Context mContext;

    private int squareLenght;

    private int firstSquareColor = getResources().getColor(R.color.red),
            secondSquareColor = getResources().getColor(R.color.green),
            thirdSquareColor = getResources().getColor(R.color.blue),
            forthSquareColor = getResources().getColor(R.color.grey);

    private SquareLayout firstSquare, secondSquare, thirdSquare, forthSquare;

    private int aminDuration = 500;


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
    }

    public void startAmination() {

        forthSquare.setPivotX(100);
        forthSquare.setPivotY(0);
        ObjectAnimator animator = ObjectAnimator.ofFloat(forthSquare, "rotationX", 0, 180);
        animator.setDuration(aminDuration);
        animator.start();


        thirdSquare.setPivotX(0);
        thirdSquare.setPivotY(0);
        ObjectAnimator anim = ObjectAnimator.ofFloat(thirdSquare, "rotationX", 0, 180);
        anim.setDuration(aminDuration);
        anim.start();

        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                thirdSquare.setVisibility(View.GONE);

                secondSquare.setPivotX(0);
                ObjectAnimator anim2 = ObjectAnimator.ofFloat(secondSquare, "rotationY", 0, -
                        180);
                anim2.setDuration(aminDuration);
                anim2.start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }

    private int dpToPx(Context context, int dp) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, metrics);
    }


}
