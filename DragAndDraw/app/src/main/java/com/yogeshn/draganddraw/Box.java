package com.yogeshn.draganddraw;

import android.graphics.PointF;
import android.os.Parcel;
import android.os.Parcelable;

public class Box implements Parcelable {
    private static final long serialVersionUID = 0L;

    private PointF mOrigin;
    private PointF mCurrent;
    private PointF mRotationOrigin;
    private PointF mRotationCurrent;

    public Box(PointF origin) {
        mOrigin = mCurrent = mRotationCurrent = mRotationOrigin = origin;
    }

    public void setmCurrent(PointF current) {
        mCurrent = current;
    }

    public PointF getmCurrent() {
        return mCurrent;
    }

    public PointF getmOrigin() {
        return mOrigin;
    }

    public float getmRotationDegree() {
        float slopeCurrent = 0;
        float slopeOrigin = 0;
        if (mCurrent.x - mRotationCurrent.x != 0)
            slopeCurrent = (mCurrent.y - mRotationCurrent.y) / (Math.abs(mCurrent.x - mRotationCurrent.x));
        if (mCurrent.x - mRotationOrigin.x != 0)
            slopeOrigin = (mCurrent.y - mRotationOrigin.y) / (Math.abs(mCurrent.x - mRotationOrigin.x));
        return (float) (Math.toDegrees(Math.atan(slopeCurrent) - Math.atan(slopeOrigin)));
    }

    public void setmRotationOrigin(PointF rotationOrigin) {
        mRotationOrigin = mRotationCurrent = rotationOrigin;
    }

    public void setmRotationCurrent(PointF rotationCurrent) {
        mRotationCurrent = rotationCurrent;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(mOrigin);
        dest.writeValue(mCurrent);
    }
}
