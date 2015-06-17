package belows.com.tangshi.domain;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by belows on 15/6/11.
 */
@DatabaseTable
public class Poem implements Serializable {

    @DatabaseField(id = true, index = true)
    public int mId;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public Verse mTitle;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public Verse mAuthor;

    @DatabaseField
    public String mAuthorName;

    @DatabaseField
    public String mCategory;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<Verse> mContentList;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<String> mCommentList;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<String> mRhymeList;

    @DatabaseField(dataType = DataType.SERIALIZABLE)
    public ArrayList<String> mAppreciationList;
}
