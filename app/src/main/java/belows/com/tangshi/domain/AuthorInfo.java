package belows.com.tangshi.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.ArrayList;

/**
 * Created by belows on 15/6/11.
 */
@JsonIgnoreProperties (ignoreUnknown = true)
@DatabaseTable
public class AuthorInfo {

    @DatabaseField (generatedId = true)
    public int id;

    @DatabaseField
    public String name;

    @DatabaseField (dataType = DataType.SERIALIZABLE)
    public ArrayList<String> pList = new ArrayList<String>();

    @DatabaseField
    public int worksCount = 0;

    public AuthorInfo() {
        id = 0;
    }
}
