package belows.com.tangshi.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.yy.androidlib.util.logging.Logger;

import belows.com.tangshi.R;
import belows.com.tangshi.activity.fragment.MainFragment;
import belows.com.tangshi.domain.MenuItem;
import belows.com.tangshi.widget.SlidingLayout;
import belows.com.tangshi.widget.SlidingMenu;

/**
 * Created by belows on 15/6/10.
 */
public class MainActivity extends FrameActivity implements SlidingMenu.OnMenuClickListener {

    private SlidingLayout mSlidingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initMainFragment();

    }

    private void initViews() {
        mSlidingLayout = (SlidingLayout) findViewById(R.id.sl_layout);
        mSlidingLayout.setMenuClickListener(this);
    }

    @Override
    protected boolean showTitle() {
        return false;
    }

    @Override
    public void onMenuClick(MenuItem pItem) {
        mSlidingLayout.toggle();
        replaceFragment(pItem.mFragmentClass);
    }

    private void initMainFragment() {
        FragmentManager _fm = getSupportFragmentManager();
        FragmentTransaction _ft = _fm.beginTransaction();
        _ft.add(R.id.sliding_content, new MainFragment());
        _ft.commitAllowingStateLoss();
    }

    private void replaceFragment(Class<?> pTargetFragment) {
        try {
            FragmentManager _fm = getSupportFragmentManager();
            FragmentTransaction _ft = _fm.beginTransaction();
            _ft.replace(R.id.sliding_content, (Fragment) pTargetFragment.newInstance());
            _ft.commitAllowingStateLoss();
        } catch (Exception e) {
            Logger.info("replace fragment error", e.toString());
        }
    }
}
