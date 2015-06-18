package belows.com.tangshi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;

import belows.com.tangshi.R;
import belows.com.tangshi.domain.Verse;

/**
 * Created by belows on 15/6/18.
 */
public class VerseView extends LinearLayout {

    private TextView mPinyinTextView;
    private TextView mContentTextView;

    private float mPinyinTextSize;
    private float mContentTextSize;
    private int mPinyinTextColor;
    private int mContentTextColor;

    public VerseView(Context context) {
        this(context, null);
    }

    public VerseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);
        setGravity(Gravity.CENTER_HORIZONTAL);

        initVariables(context, attrs);
        initViews(context);
    }

    public void update(Verse pVerse) {
        mPinyinTextView.setText(pVerse.mPinyin);
        mContentTextView.setText(pVerse.mContent);
    }

    private void initVariables(Context pContext, AttributeSet pAttrs) {
        TypedArray _typedArray = pContext.obtainStyledAttributes(pAttrs, R.styleable.VerseView);
        mPinyinTextSize = _typedArray.getDimension(R.styleable.VerseView_pinyin_text_size, 15);
        mContentTextSize = _typedArray.getDimension(R.styleable.VerseView_content_text_size, 20);
        mPinyinTextColor = _typedArray.getColor(R.styleable.VerseView_pinyin_text_color, Color.RED);
        mContentTextColor = _typedArray.getColor(R.styleable.VerseView_content_text_color, Color.BLACK);
        _typedArray.recycle();
    }

    private void initViews(Context pContext) {
        mPinyinTextView = new TextView(pContext);
        mContentTextView = new TextView(pContext);

        mPinyinTextView.setGravity(Gravity.CENTER);
        mContentTextView.setGravity(Gravity.CENTER);

        mPinyinTextView.setTextColor(mPinyinTextColor);
        mContentTextView.setTextColor(mContentTextColor);
        mPinyinTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mPinyinTextSize);
        mContentTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContentTextSize);
        addView(mPinyinTextView);
        addView(mContentTextView);
    }
}
