package belows.com.tangshi.repository;

import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;
import com.j256.ormlite.support.ConnectionSource;
import com.yy.androidlib.util.logging.Logger;

import java.util.ArrayList;
import java.util.List;

import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.domain.Verse;

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

    public long size(String pKey, String pValue) {
        try {
            QueryBuilder<Poem, Integer> _builder = mDao.queryBuilder();
            Where<Poem, Integer> _where = _builder.where();
            _where.eq(pKey, pValue);
            _builder.setWhere(_where);
            return _builder.countOf();
        } catch (Exception e) {
            return 0;
        }
    }

    public List<Poem> queryAuthor(String pAuthor) {
        try {
            QueryBuilder<Poem, Integer> _builder = mDao.queryBuilder();
            Where<Poem, Integer> _where = _builder.where();
            _where.eq("mAuthorName", pAuthor);
            _builder.setWhere(_where);
            return _builder.query();
        } catch (Exception e) {
            Logger.error("author tangshi query error:", e.toString());
            return new ArrayList<Poem>();
        }
    }

    public List<Poem> queryCategory(String pCategory) {
        try {
            QueryBuilder<Poem, Integer> _builder = mDao.queryBuilder();
            Where<Poem, Integer> _where = _builder.where();
            _where.eq("mCategory", pCategory);
            _builder.setWhere(_where);
            return _builder.query();
        } catch (Exception e) {
            Logger.error("category tangshi query error:", e.toString());
            return new ArrayList<Poem>();
        }
    }

    public List<Poem> query(String pName, String pAuthor) {
        try {
            QueryBuilder<Poem, Integer> _builder = mDao.queryBuilder();
            Where<Poem, Integer> _where = _builder.where();
            _where.eq("mTitleName", pName);
            _where.and();
            _where.eq("mAuthorName", pAuthor);
            _builder.setWhere(_where);
            return _builder.query();
        } catch (Exception e) {
            Logger.error("query tangshi error:", e.toString());
            return new ArrayList<Poem>();
        }
    }
}
