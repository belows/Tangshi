package belows.com.tangshi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yy.androidlib.util.apache.StringUtils;

import belows.com.tangshi.R;

/**
 * Created by belows on 15/6/10.
 */
public class TitleBar extends LinearLayout {

    private TextView mLeftTextView;
    private TextView mRightTextView;
    private TextView mTitleTextView;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflate(context, R.layout.layout_title_bar, this);

        initViews();
    }

    private void initViews() {
        int _height = (int) getResources().getDimension(R.dimen.title_bar_height);
        ViewGroup.LayoutParams _lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, _height);
        setLayoutParams(_lp);

        mLeftTextView = (TextView) findViewById(R.id.tv_left);
        mRightTextView = (TextView) findViewById(R.id.tv_right);
        mTitleTextView = (TextView) findViewById(R.id.tv_title);
    }

    public void setTextColor(int pColor) {
        mLeftTextView.setTextColor(pColor);
        mTitleTextView.setTextColor(pColor);
        mRightTextView.setTextColor(pColor);
    }

    public void setTitle(int pStrId) {
        mTitleTextView.setText(pStrId);
    }

    public void setTitle(String pTitle) {
        mTitleTextView.setText(pTitle);
    }

    public void setLeftText(int pLeftTextId, int pDrawableId, OnClickListener pClickListener) {
        if (pLeftTextId != 0) {
            mLeftTextView.setText(pLeftTextId);
        }
        if (pDrawableId != 0) {
            mLeftTextView.setCompoundDrawables(getResources().getDrawable(pDrawableId), null, null, null);
        }
        mLeftTextView.setOnClickListener(pClickListener);
    }

    public void setLeftText(String pLeftText, int pDrawableId, OnClickListener pClickListener) {
        if (!StringUtils.isEmpty(pLeftText)) {
            mLeftTextView.setText(pLeftText);
        }
        if (pDrawableId != 0) {
            mLeftTextView.setCompoundDrawables(getResources().getDrawable(pDrawableId), null, null, null);
        }
        mLeftTextView.setOnClickListener(pClickListener);
    }

    public void setRightText(int pRightTextId, int pDrawableId, OnClickListener pClickListener) {
        if (pRightTextId != 0) {
            mRightTextView.setText(pRightTextId);
        }
        if (pDrawableId != 0) {
            mRightTextView.setCompoundDrawables(getResources().getDrawable(pDrawableId), null, null, null);
        }
        mRightTextView.setOnClickListener(pClickListener);
    }

    public void setRightText(String pRightText, int pDrawableId, OnClickListener pClickListener) {
        if (!StringUtils.isEmpty(pRightText)) {
            mRightTextView.setText(pRightText);
        }
        if (pDrawableId != 0) {
            mRightTextView.setCompoundDrawables(getResources().getDrawable(pDrawableId), null, null, null);
        }
        mRightTextView.setOnClickListener(pClickListener);
    }
}
