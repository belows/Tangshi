package belows.com.tangshi.callbacks;

import java.util.List;

import belows.com.tangshi.domain.AuthorInfo;
import belows.com.tangshi.domain.Category;
import belows.com.tangshi.domain.Collection;
import belows.com.tangshi.domain.MingJu;
import belows.com.tangshi.domain.PlayList;
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

    public interface IMingJu {
        void onMingJuAck(List<MingJu> pMingJuList);
    }

    public interface ICollection {
        void onCollectionAck(List<Collection> pCollectionList);
    }

    public interface IPlayList {
        void onPlayListAck(List<PlayList> pPlayList);
    }
}
