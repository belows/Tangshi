package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.yy.androidlib.util.notification.NotificationCenter;
import com.yy.androidlib.util.sdk.BaseAdapter;

import belows.com.tangshi.R;
import belows.com.tangshi.widget.TitleBar;

/**
 * Created by belows on 15/6/10.
 */
public abstract class FrameFragment<T> extends BaseFragment {
    private LinearLayout mRootView;
    private TitleBar mTitleBar;
    protected ListView mListView;
    protected BaseAdapter<T> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
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

    protected View customView(LayoutInflater pInflater) {
        mListView = (ListView) pInflater.inflate(R.layout.listview, null);
        initAdapter();
        return mListView;
    }

    protected void initAdapter() {
        mAdapter = new BaseAdapter<T>() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                return customListItemView(getItem(position), position, convertView, parent);
            }
        };
        mListView.setAdapter(mAdapter);
    }

    protected View customListItemView(T item, int position, View convertView, ViewGroup parent) {
        return null;
    }
}
