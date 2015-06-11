package belows.com.tangshi.domain;

import java.io.Serializable;

/**
 * Created by belows on 15/6/11.
 */
public class Verse implements Serializable {
    public String mPinyin;
    public String mContent;

    public Verse(String pPinyin, String pContent) {
        mPinyin = pPinyin;
        mContent = pContent;
    }

    public Verse() {}
}
