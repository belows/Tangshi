package belows.com.tangshi.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yy.androidlib.util.notification.NotificationCenter;

/**
 * Created by belows on 15/6/12.
 */
public class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        NotificationCenter.INSTANCE.addObserver(this);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        NotificationCenter.INSTANCE.removeObserver(this);
    }

    public void startActivity(Class<?> pClass) {
        Intent _intent = new Intent(getActivity(), pClass);
        getActivity().startActivity(_intent);
    }
}
