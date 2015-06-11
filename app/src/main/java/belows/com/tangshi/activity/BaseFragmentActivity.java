package belows.com.tangshi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Gravity;
import android.widget.Toast;

import com.yy.androidlib.util.notification.NotificationCenter;

import belows.com.tangshi.utils.ToastUtil;

/**
 * Created by belows on 15/6/10.
 */
public class BaseFragmentActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        NotificationCenter.INSTANCE.addObserver(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        NotificationCenter.INSTANCE.removeObserver(this);
    }

    public void showToast(String pMsg) {
        ToastUtil.show(this, pMsg);
    }

    public void showLongToast(String pMsg) {
        ToastUtil.show(this, pMsg, Gravity.CENTER, Toast.LENGTH_LONG);
    }

    public void startActivity(Class<?> pClass) {
        Intent intent = new Intent(this, pClass);
        startActivity(intent);
    }
}
