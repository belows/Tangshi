package belows.com.tangshi.utils;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

import belows.com.tangshi.R;

/**
 * Created by belows on 15/6/10.
 */
public class ToastUtil {
    private static WeakReference<Toast> toastWeakReference = new WeakReference<Toast>(null);

    public static void show(Context pContext, int pStrId) {
        show(pContext, pContext.getString(pStrId), Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    public static void show(Context pContext, String pMsg) {
        show(pContext, pMsg, Gravity.CENTER, Toast.LENGTH_SHORT);
    }

    public static void show(Context pContext, String pMsg, int pGravity, int pDuration) {
        Toast _toast = toastWeakReference.get();
        if (_toast == null) {
            _toast = makeToast(pContext);
            toastWeakReference = new WeakReference<Toast>(_toast);
        }
        TextView _textView = (TextView) _toast.getView().findViewById(R.id.tv_toast);
        _textView.setText(pMsg);
        _toast.setGravity(pGravity, 0, 0);
        _toast.setDuration(pDuration);
        _toast.show();
    }

    private static Toast makeToast(Context pContext) {
        Toast _toast = Toast.makeText(pContext, "", Toast.LENGTH_SHORT);
        View _view = LayoutInflater.from(pContext).inflate(R.layout.layout_toast, null);
        _toast.setView(_view);
        return _toast;
    }
}
