package belows.com.tangshi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import belows.com.tangshi.R;
import belows.com.tangshi.widget.TitleBar;

/**
 * Created by belows on 15/6/10.
 */
public class FrameActivity extends BaseFragmentActivity {

    private LinearLayout mRootView;
    private TitleBar mTitleBar;
    private View mShadowView;

    private View.OnClickListener mBackClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_frame);

        initViews();
    }

    private void initViews() {
        mRootView = (LinearLayout) findViewById(R.id.ll_root);
        mTitleBar = (TitleBar) findViewById(R.id.tb_title);
        mShadowView = findViewById(R.id.view_shadow);

        if (!showTitle()) {
            mTitleBar.setVisibility(View.GONE);
            mShadowView.setVisibility(View.GONE);
        } else if (showBack()) {
            mTitleBar.setLeftText(R.string.back, 0, mBackClickListener);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        View _view = LayoutInflater.from(this).inflate(layoutResID, null);
        setContentView(_view);
    }

    @Override
    public void setContentView(View view) {
        mRootView.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        mRootView.addView(view, params);
    }

    protected boolean showTitle() {
        return true;
    }

    protected boolean showBack() {
        return true;
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitleBar.setTitle(title.toString());
    }

    @Override
    public void setTitle(int titleId) {
        mTitleBar.setTitle(titleId);
    }

    protected void setTitleBarLeftText(int pLeftTextId, int pDrawableId) {
        mTitleBar.setLeftText(pLeftTextId, pDrawableId, mBackClickListener);
    }

    protected void setTitleBarLeftText(String pLeftText, int pDrawableId) {
        mTitleBar.setLeftText(pLeftText, pDrawableId, mBackClickListener);
    }

    protected void setTitleBarLeftText(int pLeftTextId, int pDrawableId, View.OnClickListener pClickListener) {
        mTitleBar.setLeftText(pLeftTextId, pDrawableId, pClickListener);
    }

    protected void setTitleBarLeftText(String pLeftText, int pDrawableId, View.OnClickListener pClickListener) {
        mTitleBar.setLeftText(pLeftText, pDrawableId, pClickListener);
    }

    protected void setTitleBarRightText(int pRightTextId, int pDrawableId, View.OnClickListener pClickListener) {
        mTitleBar.setRightText(pRightTextId, pDrawableId, pClickListener);
    }

    protected void setTitleBarRightText(String pRightText, int pDrawableId, View.OnClickListener pClickListener) {
        mTitleBar.setRightText(pRightText, pDrawableId, pClickListener);
    }
}
