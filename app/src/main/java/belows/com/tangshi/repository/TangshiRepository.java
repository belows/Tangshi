package belows.com.tangshi.repository;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;

import belows.com.tangshi.domain.Poem;

/**
 * Created by belows on 15/6/11.
 */
public class TangshiRepository extends BaseRepository<Poem, Integer> {

    public TangshiRepository(ConnectionSource pConnectionSource) {
        super(pConnectionSource);
    }

    @Override
    protected Class getDataClass() {
        return Poem.class;
    }

    public long size(String pAuthor) {
        try {
            QueryBuilder<Poem,Integer> _builder = mDao.queryBuilder();
            Where<Poem,Integer> _where = _builder.where();
            _where.eq("mAuthorName",pAuthor);
            _builder.setWhere(_where);
            return _builder.countOf();
        } catch (Exception e) {
            return 0;
        }
    }
}
