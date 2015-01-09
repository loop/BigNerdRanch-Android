package com.yogeshn.draganddraw;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class BoxDrawingView extends View {
    public static final String TAG = "BoxDrawingView";

    private Box mCurrentBox;
    private ArrayList<Box> mBoxes = new ArrayList<Box>();
    private Paint mBoxPaint;
    private Paint mBackgroundPaint;

    public BoxDrawingView(Context context) {
        this(context, null);
    }

    public BoxDrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Paint the boxes a nice semitransparent red (ARGB)
        mBoxPaint = new Paint();
        mBoxPaint.setColor(0x22ff0000);

        // Paint the background off-white
        mBackgroundPaint = new Paint();
        mBackgroundPaint.setColor(0xfff8efe0);
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putParcelableArrayList("boxes", mBoxes);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            this.mBoxes = bundle.getParcelableArrayList("boxes");
            state = bundle.getParcelable("instanceState");
        }
        super.onRestoreInstanceState(state);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawPaint(mBackgroundPaint);

        for (Box box : mBoxes) {
            float left = Math.min(box.getmOrigin().x, box.getmCurrent().x);
            float right = Math.max(box.getmOrigin().x, box.getmCurrent().x);
            float top = Math.min(box.getmOrigin().y, box.getmCurrent().y);
            float bottom = Math.max(box.getmOrigin().y, box.getmCurrent().y);
            canvas.save();
            canvas.rotate(box.getmRotationDegree(), box.getmCurrent().x, box.getmCurrent().y);
            canvas.drawRect(left, top, right, bottom, mBoxPaint);
            canvas.restore();

        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        PointF curr0 = new PointF(event.getX(0), event.getY(0));
        ;
        PointF curr1 = null;

        Log.i(TAG, "Box drawer at x=" + curr0.x + ", y=" + curr0.y + ":");

        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                Log.i(TAG, "  ACTION DOWN");
                mCurrentBox = new Box(curr0);
                mBoxes.add(mCurrentBox);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                if (mCurrentBox != null) {
                    curr1 = new PointF(event.getX(1), event.getY(1));
                    mCurrentBox.setmRotationOrigin(curr1);
                    Log.i(TAG, "  ACTION DOWN SECOND FINGER " + curr1.x + "," + curr1.y);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                Log.i(TAG, "  ACTION MOVE");
                if (mCurrentBox != null) {
                    mCurrentBox.setmCurrent(curr0);
                    if (event.getPointerCount() > 1) {
                        Log.i(TAG, "  ACTION MOVE SECOND FINGER");
                        curr1 = new PointF(event.getX(1), event.getY(1));
                        Log.i(TAG, "  ACTION MOVE SECOND FINGER " + curr1.x + "," + curr1.y);
                        mCurrentBox.setmRotationCurrent(curr1);
                    }
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.i(TAG, "  ACTION UP");
                mCurrentBox = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.i(TAG, "  ACTION CANCEL");
                mCurrentBox = null;
                break;
        }
        return true;
    }
}
