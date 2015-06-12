package belows.com.tangshi.repository;

import com.j256.ormlite.support.ConnectionSource;

import belows.com.tangshi.domain.Collection;

/**
 * Created by belows on 15/6/12.
 */
public class CollectionRepository extends BaseRepository<Collection, Integer> {

    public CollectionRepository(ConnectionSource pConnectionSource) {
        super(pConnectionSource);
    }

    @Override
    protected Class getDataClass() {
        return Collection.class;
    }
}
