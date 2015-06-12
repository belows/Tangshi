package belows.com.tangshi.domain;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by belows on 15/6/12.
 */
@DatabaseTable
public class MingJu {

    @DatabaseField(generatedId = true, index = true)
    public int mId;

    @DatabaseField
    public String mContent;

    @DatabaseField
    public String mAuthor;

    @DatabaseField
    public String mPoemTitle;
}
