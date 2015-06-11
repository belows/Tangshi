package belows.com.tangshi.model;

import android.app.Application;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;

import com.j256.ormlite.android.AndroidConnectionSource;
import com.yy.androidlib.util.logging.Logger;
import com.yy.androidlib.util.logging.YYAppender;
import com.yy.androidlib.util.notification.NotificationCenter;

import java.io.File;

import belows.com.tangshi.callbacks.TangshiCallback;

/**
 * Created by belows on 15/6/10.
 */
public enum AppModel {
    INSTANCE;

    private static final String DATABASE_NAME = "belows_tangshi";

    private boolean mHasInit = false;

    private Handler mMainThreadHandler;
    private Handler mIoThreadHandler;
    private Application mApplication;

    private YYAppender mLogAppender;
    private HttpServer mHttpServer;
    private AndroidConnectionSource mConnectionSource;

    private TangshiModel mTangshiModel;

    private AppModel() {
    }

    public void init(Application pApplication) {
        mApplication = pApplication;
        mHasInit = true;
        mMainThreadHandler = new Handler(Looper.getMainLooper());
        HandlerThread _IoThread = new HandlerThread("IoThread");
        _IoThread.start();
        mIoThreadHandler = new Handler(_IoThread.getLooper());

        mHttpServer = new HttpServer();

        initDatabaseConnection();

        initCallbacks();
        initModels();
        initLogging();
    }

    public boolean hasInit() {
        return mHasInit;
    }

    public void runInMainHandler(Runnable pRunnable) {
        mMainThreadHandler.post(pRunnable);
    }

    public static String getAppDir() {
        return getAppDir("belows_tangshi");
    }

    public static String getAppDir(String pAppName) {
        return Environment.getExternalStorageDirectory().getPath() + File.separator + pAppName;
    }

    public void recycle() {
        mTangshiModel.recycle();
    }

    public TangshiModel tangshi() {
        if (mTangshiModel == null) {
            mTangshiModel = new TangshiModel();
            mTangshiModel.setConnectionSource(mConnectionSource);
            mTangshiModel.init(mApplication, mIoThreadHandler);
        }
        return mTangshiModel;
    }

    private void initCallbacks() {
        NotificationCenter.INSTANCE.addCallbacks(TangshiCallback.class);
    }

    private void initModels() {
        tangshi();
    }

    private void initLogging() {
        YYAppender.LogOptions _options = new YYAppender.LogOptions();
        _options.logFileName = "logs.txt";
        _options.logLevel = YYAppender.LogOptions.LEVEL_INFO;
        _options.honorVerbose = false;
        String _logTag = "belows_tangshi";
        mLogAppender = new YYAppender(getAppDir(_logTag), _options);
        mLogAppender.setUniformTag(_logTag);
        mLogAppender.setCallerStackTraceIndex(7);
        Logger.init(mLogAppender);
    }

    private void initDatabaseConnection() {
        SQLiteDatabase _dataBase = null;
        try {
            _dataBase = mApplication.openOrCreateDatabase(DATABASE_NAME, 1, null);
        } catch (SQLException e) {
            String _path = mApplication.getDatabasePath(DATABASE_NAME).getPath();
            _dataBase = SQLiteDatabase.openDatabase(_path, null, SQLiteDatabase.OPEN_READONLY);
        }
        mConnectionSource = new AndroidConnectionSource(_dataBase);
    }
}
