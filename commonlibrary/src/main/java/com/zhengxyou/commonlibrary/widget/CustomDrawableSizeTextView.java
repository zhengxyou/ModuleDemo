package com.zhengxyou.commonlibrary.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.zhengxyou.commonlibrary.R;

/**
 * 可以设置TextView上下左右图大小
 */
public class CustomDrawableSizeTextView extends android.support.v7.widget.AppCompatTextView {
    public CustomDrawableSizeTextView(Context context) {
        super(context);
        init(null, 0);
    }

    public CustomDrawableSizeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public CustomDrawableSizeTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs, defStyleAttr);
    }

    private void init(AttributeSet attrs, int defStyleAttr) {
        @SuppressLint("CustomViewStyleable") final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.CustomDrawableSizeTextView, defStyleAttr, 0);

        if (a.getBoolean(R.styleable.CustomDrawableSizeTextView_cdst_isSetting, false)) {
            int topWidth = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_TopWidth, 0);
            int topHeight = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_TopHeight, 0);

            int bottomWidth = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_BottomWidth, 0);
            int bottomHeight = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_BottomHeight, 0);

            int rightWidth = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_RightWidth, 0);
            int rightHeight = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_RightHeight, 0);

            int leftWidth = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_LeftWidth, 0);
            int leftHeight = (int) a.getDimension(R.styleable.CustomDrawableSizeTextView_cdst_LeftHeight, 0);


            Drawable[] drawables = this.getCompoundDrawables();

            //取得right位置的Drawable
            //即我们在布局文件中设置的android:drawableRight
            Drawable mLeftDrawable = drawables[0];
            Drawable mTopDrawable = drawables[1];
            Drawable mRightDrawable = drawables[2];
            Drawable mBottomDrawable = drawables[3];


            if (mTopDrawable != null) {
                mTopDrawable.setBounds(0, 0, topWidth, topHeight);
            }

            if (mLeftDrawable != null) {
                mLeftDrawable.setBounds(0, 0, leftWidth, leftHeight);
            }

            if (mRightDrawable != null) {
                mRightDrawable.setBounds(0, 0, rightWidth, rightHeight);
            }

            if (mBottomDrawable != null) {
                mBottomDrawable.setBounds(0, 0, bottomWidth, bottomHeight);
            }

            setCompoundDrawables(mLeftDrawable, mTopDrawable, mRightDrawable, mBottomDrawable);
        }

        a.recycle();
    }
}
