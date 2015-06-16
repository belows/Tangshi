package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import belows.com.tangshi.R;

/**
 * Created by belows on 15/6/10.
 */
public class SettingFragment extends FrameFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = super.onCreateView(inflater, container, savedInstanceState);

        setTitle(getString(R.string.setting));
        return _root;
    }

    @Override
    protected View customView(LayoutInflater pInflater) {
        return new TextView(getActivity());
    }
}
