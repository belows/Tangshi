package belows.com.tangshi.domain;

/**
 * Created by belows on 15/6/10.
 */
public class MenuItem {
    public String mItemName;
    public Class<?> mFragmentClass;
    public int mDrawableId;

    public MenuItem(String pItemName, int pDrawableId, Class<?> pFragmentClass) {
        mItemName = pItemName;
        mDrawableId = pDrawableId;
        mFragmentClass = pFragmentClass;
    }
}
