package com.agrawalsuneet.fourfold.dialog.helper;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Suneet on 13/01/17.
 */

public class LoaderController implements Parcelable {

    public float textSize;
    public int textColor;
    public String message;
    public int squareLength;
    private boolean overridePadding;
    public int firstSquareColor, secondSquareColor,
            thirdSquareColor, forthSquareColor;
    public int animDur, fadeAnimDuration;
    public int background;

    public LoaderController() {
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getSquareLength() {
        return squareLength;
    }

    public void setSquareLength(int squareLength) {
        this.squareLength = squareLength;
    }

    public boolean isOverridePadding() {
        return overridePadding;
    }

    public void setOverridePadding(boolean overridePadding) {
        this.overridePadding = overridePadding;
    }

    public int getFirstSquareColor() {
        return firstSquareColor;
    }

    public void setFirstSquareColor(int firstSquareColor) {
        this.firstSquareColor = firstSquareColor;
    }

    public int getSecondSquareColor() {
        return secondSquareColor;
    }

    public void setSecondSquareColor(int secondSquareColor) {
        this.secondSquareColor = secondSquareColor;
    }

    public int getThirdSquareColor() {
        return thirdSquareColor;
    }

    public void setThirdSquareColor(int thirdSquareColor) {
        this.thirdSquareColor = thirdSquareColor;
    }

    public int getForthSquareColor() {
        return forthSquareColor;
    }

    public void setForthSquareColor(int forthSquareColor) {
        this.forthSquareColor = forthSquareColor;
    }

    public int getAnimDur() {
        return animDur;
    }

    public void setAnimDur(int animDur) {
        this.animDur = animDur;
    }

    public int getFadeAnimDuration() {
        return fadeAnimDuration;
    }

    public void setFadeAnimDuration(int fadeAnimDuration) {
        this.fadeAnimDuration = fadeAnimDuration;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    protected LoaderController(Parcel in) {
        textSize = in.readFloat();
        textColor = in.readInt();
        message = in.readString();
        squareLength = in.readInt();
        overridePadding = in.readByte() != 0;
        firstSquareColor = in.readInt();
        secondSquareColor = in.readInt();
        thirdSquareColor = in.readInt();
        forthSquareColor = in.readInt();
        animDur = in.readInt();
        fadeAnimDuration = in.readInt();
        background = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(textSize);
        dest.writeInt(textColor);
        dest.writeString(message);
        dest.writeInt(squareLength);
        dest.writeByte((byte) (overridePadding ? 1 : 0));
        dest.writeInt(firstSquareColor);
        dest.writeInt(secondSquareColor);
        dest.writeInt(thirdSquareColor);
        dest.writeInt(forthSquareColor);
        dest.writeInt(animDur);
        dest.writeInt(fadeAnimDuration);
        dest.writeInt(background);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<LoaderController> CREATOR = new Creator<LoaderController>() {
        @Override
        public LoaderController createFromParcel(Parcel in) {
            return new LoaderController(in);
        }

        @Override
        public LoaderController[] newArray(int size) {
            return new LoaderController[size];
        }
    };
}

