package belows.com.tangshi.callbacks;

import java.util.List;

import belows.com.tangshi.domain.AuthorInfo;
import belows.com.tangshi.domain.Category;
import belows.com.tangshi.domain.Poem;

/**
 * Created by belows on 15/6/11.
 */
public interface TangshiCallback {
    public interface Tangshi {
        void onTangshiAck(List<Poem> pTangshiList);
    }

    public interface Author {
        void onAuthorsAck(List<AuthorInfo> pInfoList);
    }

    public interface Category {
        void onCategoryAck(List<belows.com.tangshi.domain.Category> pCategoryList);
    }
}
