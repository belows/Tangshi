package belows.com.tangshi.repository;

import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by belows on 15/6/10.
 */
public abstract class BaseUserRepository<T, ID> extends BaseRepository<T, ID> {

    private long mUid = -1;

    public BaseUserRepository(ConnectionSource pConnectionSource) {
        super(pConnectionSource);
    }

    public void updateUser(long pUid) {
        if (this.mUid == pUid) {
            return;
        }
        mUid = pUid;
        init();
    }

    @Override
    protected String tableName() {
        return getDataClass().getSimpleName() + "_" + mUid + "_datatable";
    }
}
