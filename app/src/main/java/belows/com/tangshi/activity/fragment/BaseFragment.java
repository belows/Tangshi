package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.yy.androidlib.util.notification.NotificationCenter;

import belows.com.tangshi.R;
import belows.com.tangshi.widget.TitleBar;

/**
 * Created by belows on 15/6/10.
 */
public abstract class BaseFragment extends Fragment {
    private LinearLayout mRootView;
    private TitleBar mTitleBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NotificationCenter.INSTANCE.addObserver(this);
        mRootView = (LinearLayout) inflater.inflate(R.layout.fragment_frame, container, false);
        mTitleBar = (TitleBar) mRootView.findViewById(R.id.tb_title);
        mRootView.addView(customView(inflater));
        return mRootView;
    }

    protected void setTitleColor(int pColor) {
        mTitleBar.setTextColor(pColor);
    }

    protected void setTitle(String pTitle) {
        mTitleBar.setTitle(pTitle);
    }

    protected abstract View customView(LayoutInflater pInflater);

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        NotificationCenter.INSTANCE.removeObserver(this);
    }
}
