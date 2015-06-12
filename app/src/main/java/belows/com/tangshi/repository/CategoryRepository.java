package belows.com.tangshi.repository;

import com.j256.ormlite.support.ConnectionSource;

import belows.com.tangshi.domain.Category;

/**
 * Created by belows on 15/6/12.
 */
public class CategoryRepository extends BaseRepository<Category, Integer> {

    public CategoryRepository(ConnectionSource pConnectionSource) {
        super(pConnectionSource);
    }

    @Override
    protected Class getDataClass() {
        return Category.class;
    }
}
