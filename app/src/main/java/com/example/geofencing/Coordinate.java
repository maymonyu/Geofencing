package com.example.geofencing;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

public class Coordinate extends View {
    private String mText;
    private String mValue;

    public Coordinate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);

        TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.Coordinate,
                0, 0);
        try {
            mText = typedArray.getString(R.styleable.Coordinate_coordinateText);
            mValue = typedArray.getString(R.styleable.Coordinate_coordinateValue);
        } finally {
            typedArray.recycle();
        }
    }

    public Coordinate(Context context) {
        super(context);
        init(null);
    }

    public Coordinate(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Coordinate(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    private void init(@Nullable AttributeSet set){

    }
}