package belows.com.tangshi;

import android.app.Application;

import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        if (!AppModel.INSTANCE.hasInit()) {
            AppModel.INSTANCE.init(this);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        AppModel.INSTANCE.recycle();
    }
}
