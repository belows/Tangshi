package belows.com.tangshi.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by belows on 15/6/12.
 */
@DatabaseTable
public class Collection {

    @DatabaseField(generatedId = true, index = true)
    public int mId;

    @DatabaseField(unique = true)
    public int mPoemId;

    @DatabaseField
    public String mPoemTitle;

    @DatabaseField
    public String mAuthor;

    public Collection() {
    }

    public Collection(int pPoemId, String pPoemTitle, String pAuthor) {
        mPoemId = pPoemId;
        mPoemTitle = pPoemTitle;
        mAuthor = pAuthor;
    }
}
