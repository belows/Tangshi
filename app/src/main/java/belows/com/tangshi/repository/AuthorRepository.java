package belows.com.tangshi.repository;

import com.j256.ormlite.support.ConnectionSource;

import belows.com.tangshi.domain.AuthorInfo;

/**
 * Created by belows on 15/6/11.
 */
public class AuthorRepository extends BaseRepository<AuthorInfo, Integer> {

    public AuthorRepository(ConnectionSource pConnectionSource) {
        super(pConnectionSource);
    }

    @Override
    protected Class getDataClass() {
        return AuthorInfo.class;
    }
}
