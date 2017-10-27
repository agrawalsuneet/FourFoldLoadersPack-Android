package com.agrawalsuneet.fourfold.dialog;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agrawalsuneet.fourfold.R;
import com.agrawalsuneet.fourfold.dialog.helper.LoaderController;
import com.agrawalsuneet.fourfoldloader.loaders.FourFoldLoader;

/**
 * Created by Suneet on 13/01/17.
 */

public class FourFoldDialog extends DialogFragment {

    private View mView;
    private TextView mMessageTextView;
    private LoaderController mController;
    private LinearLayout mContainerLL;
    private FourFoldLoader mLoader;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null != savedInstanceState) {
            mController = savedInstanceState.getParcelable("controller");
        }
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        mView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_loader, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(mView);

        AlertDialog dialog = builder.create();
        initViews(dialog);
        return dialog;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("controller", mController);
    }

    public void setValues(LoaderController controller) {
        this.mController = controller;
    }

    private void initViews(AlertDialog dialog) {
        mContainerLL = (LinearLayout) mView.findViewById(R.id.dialog_container);
        mMessageTextView = (TextView) mView.findViewById(R.id.dialog_message_tv);
        mLoader = (FourFoldLoader) mView.findViewById(R.id.fourfoldloader);

        if (mController.background != 0) {
            mContainerLL.setBackgroundColor(ContextCompat.getColor(getContext(), mController.background));
        } else {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        }

        if (!TextUtils.isEmpty(mController.message)) {
            mMessageTextView.setText(mController.message);
        }

        if (mController.textSize != 0) {
            mMessageTextView.setTextSize(mController.textSize);
        }

        if (mController.textColor != 0) {
            mMessageTextView.setTextColor(ContextCompat.getColor(getContext(), mController.textColor));
        }

        if (mController.squareLength != 0){
            mLoader.setSquareLenght(mController.squareLength);
        }

        if (mController.animDur != 0) {
            mLoader.setAnimationDuration(mController.animDur);
        }

        if (mController.fadeAnimDuration != 0){
            mLoader.setDisappearAnimationDuration(mController.fadeAnimDuration);
        }

        if (mController.firstSquareColor != 0) {
            mLoader.setFirstSquareColor(mController.firstSquareColor);
        }

        if (mController.secondSquareColor != 0) {
            mLoader.setSecondSquareColor(mController.secondSquareColor);
        }

        if (mController.thirdSquareColor != 0) {
            mLoader.setThirdSquareColor(mController.thirdSquareColor);
        }

        if (mController.forthSquareColor != 0) {
            mLoader.setForthSquareColor(mController.forthSquareColor);
        }
    }

    public static class Builder {

        private AppCompatActivity mActivity;
        private LoaderController mController;


        public Builder(AppCompatActivity activity) {
            this.mActivity = activity;
            mController = new LoaderController();
        }

        public Builder setTextSize(float size) {
            this.mController.setTextSize(size);
            return this;
        }

        public Builder setTextColor(int color) {
            this.mController.setTextColor(color);
            return this;
        }

        public Builder setMessage(String message) {
            this.mController.setMessage(message);
            return this;
        }

        public Builder startLoadingDefault(boolean startLoadingDefault){
            this.mController.setStartLoadingDefault(startLoadingDefault);
            return this;
        }

        public Builder setSquareLength(int squareLength){
            this.mController.setSquareLength(squareLength);
            return this;
        }

        public Builder setFirstSquareColor(int color){
            this.mController.setFirstSquareColor(color);
            return this;
        }

        public Builder setSecondSquareColor(int color){
            this.mController.setSecondSquareColor(color);
            return this;
        }

        public Builder setThirdSquareColor(int color){
            this.mController.setThirdSquareColor(color);
            return this;
        }

        public Builder setForthSquareColor(int color){
            this.mController.setForthSquareColor(color);
            return this;
        }

        public Builder setAnimDuration(int milliSecs) {
            this.mController.setAnimDur(milliSecs);
            return this;
        }

        public Builder setFadeAnimDuration(int milliSecs) {
            this.mController.setFadeAnimDuration(milliSecs);
            return this;
        }

        public Builder setBackground(int background) {
            this.mController.setBackground(background);
            return this;
        }


        public FourFoldDialog create() {
            FourFoldDialog dialog = new FourFoldDialog();
            dialog.setValues(this.mController);
            return dialog;
        }

        public FourFoldDialog show() {
            FourFoldDialog dialog = create();
            dialog.show(mActivity.getSupportFragmentManager(), "");
            return dialog;
        }
    }
}
