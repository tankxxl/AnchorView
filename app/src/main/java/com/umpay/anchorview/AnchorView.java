package com.umpay.anchorview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by rgz on 14-12-30.
 */
public class AnchorView extends View {

    private static final String TAG = AnchorView.class.getSimpleName();
    private final float mRelativeWidth;
    private final float mRelativeHeight;

    public AnchorView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.AnchorView);

        mRelativeWidth = clamp(attributes.getFloat(R.styleable.AnchorView_relativeWidth, 1));
        mRelativeHeight = clamp(attributes.getFloat(R.styleable.AnchorView_relativeHeight, 1));
        Log.d(TAG, "relativeWidth=" + mRelativeWidth + ", relativeHeight=" + mRelativeHeight);

        attributes.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = (int) (mRelativeWidth * getMeasuredWidth());
        int height = (int) (mRelativeHeight * getMeasuredHeight());
        Log.d(TAG, "Width=" + width + ", Height=" + height);
        setMeasuredDimension(width, height);
    }

    private float clamp(float value) {
        if (value > 1) {
            return 1;
        }
        if (value < 0) {
            return 0;
        }
        return value;
    }
}
