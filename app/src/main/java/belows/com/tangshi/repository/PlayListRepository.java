package belows.com.tangshi.repository;

import com.j256.ormlite.support.ConnectionSource;

import belows.com.tangshi.domain.PlayList;

/**
 * Created by belows on 15/6/12.
 */
public class PlayListRepository extends BaseRepository<PlayList, Integer> {

    public PlayListRepository(ConnectionSource pConnectionSource) {
        super(pConnectionSource);
    }

    @Override
    protected Class getDataClass() {
        return PlayList.class;
    }
}
