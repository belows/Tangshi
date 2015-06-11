package belows.com.tangshi.repository;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;
import com.yy.androidlib.util.logging.Logger;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import belows.com.tangshi.domain.Poem;

/**
 * Created by belows on 15/6/10.
 */
public abstract class BaseRepository<T, ID> {
    private final ConnectionSource mConnectionSource;
    protected Dao<T, ID> mDao;
    protected Object mLock = new Object();

    public BaseRepository(ConnectionSource pConnectionSource) {
        mConnectionSource = pConnectionSource;
    }

    public void init() {
        synchronized (mLock) {
            try {
                unregisterDao(mDao);
                DatabaseTableConfig<T> _tableConfig = tableConfig();
                TableUtils.createTableIfNotExists(mConnectionSource, _tableConfig);
                mDao = DaoManager.createDao(mConnectionSource, _tableConfig);
            } catch (Exception e) {
                Logger.error(this, getDataClass().getSimpleName() + "init error:" + e);
            }
        }
    }

    public void save(T data) {
        try {
            mDao.createIfNotExists(data);
        } catch (Exception e) {
            Logger.info(getDataClass().getSimpleName(), "save data error:" + e);
        }
    }

    public void save(final Collection<T> dataList) {
        try {
            mDao.callBatchTasks(new Callable<Void>() {
                @Override
                public Void call() throws Exception {
                    for (T t : dataList) {
                        mDao.createIfNotExists(t);
                    }
                    return null;
                }
            });
        } catch (Exception e) {
            Logger.error(getDataClass().getSimpleName(), "save data list error:" + e);
        }
    }

    public long size() {
        try {
            return mDao.countOf();
        } catch (Exception e) {
            Logger.error(getDataClass().getSimpleName(), "get size error:" + e);
            return 0;
        }
    }

    public void update(T data) {
        try {
            mDao.update(data);
        } catch (Exception e) {
            Logger.error(getDataClass().getSimpleName(), "update data error:" + e);
        }
    }

    public void delete(T data) {
        try {
            mDao.delete(data);
        } catch (Exception e) {
            Logger.error(getDataClass().getSimpleName(), "delete data error:" + e);
        }
    }

    public void delete(Collection<T> dataList) {
        try {
            mDao.delete(dataList);
        } catch (Exception e) {
            Logger.error(getDataClass().getSimpleName(), "delete data list error:" + e);
        }
    }

    public List<T> queryAll() {
        try {
            return mDao.queryForAll();
        } catch (Exception e) {
            Logger.error(getDataClass().getSimpleName(), "query all error:" + e);
            return null;
        }
    }

    protected abstract Class getDataClass();

    protected String tableName() {
        return getDataClass().getSimpleName() + "_datatable";
    }

    private DatabaseTableConfig<T> tableConfig() {
        synchronized (mLock) {
            return new DatabaseTableConfig<T>(getDataClass(), tableName(), null);
        }
    }

    private void unregisterDao(Dao<?, ?> pDao) {
        synchronized (mLock) {
            if (pDao != null) {
                DaoManager.unregisterDao(mConnectionSource, pDao);
            }
        }
    }
}
