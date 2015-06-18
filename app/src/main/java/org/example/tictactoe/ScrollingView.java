package org.example.tictactoe;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;

public class ScrollingView extends View {
    private Drawable mBackground;
    private int mScrollPos;

    public ScrollingView(Context context) {
        super(context);
        init(null, 0);
    }

    public ScrollingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, 0);
    }

    public ScrollingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load custom view attributes
        final TypedArray a = getContext().obtainStyledAttributes(
                attrs, R.styleable.ScrollingView, defStyle, 0);

        // Get background
        if (a.hasValue(R.styleable.ScrollingView_scrollingDrawable)) {
            mBackground = a.getDrawable(
                    R.styleable.ScrollingView_scrollingDrawable);
            mBackground.setCallback(this);
        }

        // Done with attributes
        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int contentWidth = getWidth();
        int contentHeight = getHeight();

        if (mBackground != null) {
            int max = Math.max(mBackground.getIntrinsicHeight(), mBackground.getIntrinsicWidth());
            mBackground.setBounds(0, 0, contentWidth * 4, contentHeight * 4);

            mScrollPos += 2;
            if (mScrollPos >= max) mScrollPos -= max;
            setTranslationX(-mScrollPos);
            setTranslationY(-mScrollPos);

            mBackground.draw(canvas);
            this.invalidate();
        }
    }
}
