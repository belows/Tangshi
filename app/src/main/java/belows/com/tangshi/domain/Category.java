package belows.com.tangshi.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by belows on 15/6/12.
 */
@DatabaseTable
public class Category {
    @DatabaseField(generatedId = true, index = true)
    public int mId;

    @DatabaseField
    public String mCategory;

    public int mWorksCount;

    public Category() {
    }

    public Category(String pCategory) {
        mCategory = pCategory;
    }
}
