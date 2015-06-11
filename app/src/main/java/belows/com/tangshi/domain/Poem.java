package belows.com.tangshi.domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

/**
 * Created by belows on 15/6/11.
 */
@DatabaseTable
public class Poem {

    @DatabaseField(id = true, index = true)
    public int mId;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public Verse mTitle;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public Verse mAuthor;

    @DatabaseField
    public String mAuthorName;

//    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public List<Verse> mContentList;

//    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public List<String> mCommentList;

//    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public List<String> mRhymeList;

//    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public List<String> mAppreciationList;
}
