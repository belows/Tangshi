package belows.com.tangshi.model;

import android.app.Application;
import android.os.Handler;

/**
 * Created by belows on 15/6/10.
 */
public abstract class Model {
    private Application mApplication;
    private Handler mIoHandler;

    public abstract void unInit();

    public void recycle() {
    }

    public void init(Application pApp, Handler pIoHandler) {
        mApplication = pApp;
        mIoHandler = pIoHandler;
    }

    public Application getApp() {
        return mApplication;
    }

    public void runInIoHandler(Runnable pRunnable) {
        mIoHandler.post(pRunnable);
    }

    public Handler getIoHandler() {
        return mIoHandler;
    }
}
