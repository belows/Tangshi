package belows.com.tangshi.repository;

import com.j256.ormlite.support.ConnectionSource;

import belows.com.tangshi.domain.MingJu;

/**
 * Created by belows on 15/6/12.
 */
public class MingJuRepository extends BaseRepository<MingJu, Integer> {
    public MingJuRepository(ConnectionSource pConnectionSource) {
        super(pConnectionSource);
    }

    @Override
    protected Class getDataClass() {
        return MingJu.class;
    }
}
