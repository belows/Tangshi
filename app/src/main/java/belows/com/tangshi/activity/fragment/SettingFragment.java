package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import belows.com.tangshi.R;

/**
 * Created by belows on 15/6/10.
 */
public class SettingFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View _root = inflater.inflate(R.layout.fragment_setting, container, false);
        return _root;
    }

    @Override
    protected View customView(LayoutInflater pInflater) {
        return null;
    }
}