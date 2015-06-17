package belows.com.tangshi.model;

import android.app.Application;
import android.os.Handler;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.j256.ormlite.support.ConnectionSource;
import com.yy.androidlib.util.notification.NotificationCenter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.Category;
import belows.com.tangshi.domain.MingJu;
import belows.com.tangshi.repository.AuthorRepository;
import belows.com.tangshi.repository.CategoryRepository;
import belows.com.tangshi.repository.CollectionRepository;
import belows.com.tangshi.repository.MingJuRepository;
import belows.com.tangshi.repository.PlayListRepository;
import belows.com.tangshi.repository.TangshiRepository;
import belows.com.tangshi.domain.AuthorInfo;
import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.utils.FileUtil;
import belows.com.tangshi.utils.Preference;

/**
 * Created by belows on 15/6/11.
 */

public class TangshiModel extends Model {

    private AuthorRepository mAuthorRepository;
    private TangshiRepository mTangshiRepository;
    private CategoryRepository mCategoryRepository;
    private CollectionRepository mCollectionRepository;
    private MingJuRepository mMingJuRepository;
    private PlayListRepository mPlayListRepository;

    private List<Poem> mPoemList;

    public void setConnectionSource(ConnectionSource pConnectionSource) {
        mAuthorRepository = new AuthorRepository(pConnectionSource);
        mTangshiRepository = new TangshiRepository(pConnectionSource);
        mCategoryRepository = new CategoryRepository(pConnectionSource);
        mCollectionRepository = new CollectionRepository(pConnectionSource);
        mMingJuRepository = new MingJuRepository(pConnectionSource);
        mPlayListRepository = new PlayListRepository(pConnectionSource);
    }

    @Override
    public void init(Application pApp, Handler pIoHandler) {
        super.init(pApp, pIoHandler);
        mAuthorRepository.init();
        mTangshiRepository.init();
        mCategoryRepository.init();
        mCollectionRepository.init();
        mMingJuRepository.init();
        mPlayListRepository.init();
        initData();
    }

    public void queryPlayList() {

    }

    public void queryCollection() {

    }

    public void queryAuthor() {
        runInIoHandler(new Runnable() {
            @Override
            public void run() {
                List<AuthorInfo> _infoList = mAuthorRepository.queryAll();
                for (AuthorInfo _info : _infoList) {
                    _info.worksCount = (int) mTangshiRepository.size("mAuthorName", _info.name);
                }
                NotificationCenter.INSTANCE.getObserver(TangshiCallback.Author.class).onAuthorsAck(_infoList);
            }
        });
    }

    public void queryAuthorTangshi(String pAuthor) {
        List<Poem> _poemList = mTangshiRepository.queryAuthor(pAuthor);
        NotificationCenter.INSTANCE.getObserver(TangshiCallback.Tangshi.class).onTangshiAck(_poemList);
    }

    public void queryCategoryTangshi(String pCategory) {
        List<Poem> _poemList = mTangshiRepository.queryCategory(pCategory);
        NotificationCenter.INSTANCE.getObserver(TangshiCallback.Tangshi.class).onTangshiAck(_poemList);
    }

    public void queryTangshi() {
        if (mPoemList == null || mPoemList.size() == 0) {
            mPoemList = mTangshiRepository.queryAll();
        }
        NotificationCenter.INSTANCE.getObserver(TangshiCallback.Tangshi.class).onTangshiAck(mPoemList);
    }

    public void queryCategory() {
        runInIoHandler(new Runnable() {
            @Override
            public void run() {
                List<Category> _categoryList = mCategoryRepository.queryAll();
                if (_categoryList == null) {
                    return;
                } else {
                    for (Category _category : _categoryList) {
                        _category.mWorksCount = (int) mTangshiRepository.size("mCategory", _category.mCategory);
                    }
                    NotificationCenter.INSTANCE.getObserver(TangshiCallback.Category.class).onCategoryAck(_categoryList);
                }
            }
        });
    }

    public void queryMingJu() {
        runInIoHandler(new Runnable() {
            @Override
            public void run() {
                List<MingJu> _mingJuList = mMingJuRepository.queryAll();
                NotificationCenter.INSTANCE.getObserver(TangshiCallback.IMingJu.class).onMingJuAck(_mingJuList);
            }
        });
    }

    private void initData() {
        boolean _database_inited = Preference.get(getApp(), Preference.CommonKey.TANGSHI_INITED, false);
        if (_database_inited) {
            mPoemList = mTangshiRepository.queryAll();
            return;
        } else {
            runInIoHandler(new Runnable() {
                @Override
                public void run() {
                    mMingJuRepository.save(getMingJuFromFile());
                    mCategoryRepository.save(getCategoryFromFile());
                    mPoemList = getTangshiFromFile();
                    mAuthorRepository.save(getAuthorFromFile());
                    mTangshiRepository.save(mPoemList);
                    NotificationCenter.INSTANCE.getObserver(TangshiCallback.Tangshi.class).onTangshiAck(mPoemList);
                    Preference.save(getApp(), Preference.CommonKey.TANGSHI_INITED, true);
                }
            });
        }
    }

    private List<MingJu> getMingJuFromFile() {
        try {
            String _result = "";
            _result = FileUtil.readAssetFile("mingju.txt", "utf-8");
            ObjectMapper _objectMapper = new ObjectMapper();
            List<MingJu> _infoList = _objectMapper.readValue(_result, new TypeReference<List<MingJu>>() {
            });
            return _infoList;
        } catch (Exception e) {
        }
        return null;
    }

    private List<Category> getCategoryFromFile() {
        List<Category> _categoryList = new ArrayList<Category>();
        _categoryList.add(new Category(getString(R.string.wu_yan_gu_shi)));
        _categoryList.add(new Category(getString(R.string.qi_yan_gu_shi)));
        _categoryList.add(new Category(getString(R.string.wu_yan_jue_ju)));
        _categoryList.add(new Category(getString(R.string.qi_yan_jue_ju)));
        _categoryList.add(new Category(getString(R.string.wu_yan_yue_fu)));
        _categoryList.add(new Category(getString(R.string.qi_yan_yue_fu)));
        _categoryList.add(new Category(getString(R.string.wu_yan_lv_shi)));
        _categoryList.add(new Category(getString(R.string.qi_yan_lv_shi)));
        _categoryList.add(new Category(getString(R.string.qi_ta_tang_shi)));
        return _categoryList;
    }

    private List<AuthorInfo> getAuthorFromFile() {
        try {
            String _result = "";
            _result = FileUtil.readAssetFile("author.txt", "gb2312");
            ObjectMapper _objectMapper = new ObjectMapper();
            List<AuthorInfo> _infoList = _objectMapper.readValue(_result, new TypeReference<List<AuthorInfo>>() {
            });
            return _infoList;
        } catch (Exception e) {
        }
        return null;
    }

    private List<Poem> getTangshiFromFile() {
        try {
            String _result = "";
            _result = FileUtil.readAssetFile("tangshi.txt", "gb2312");
            ObjectMapper _objectMapper = new ObjectMapper();
            List<Poem> _infoList = _objectMapper.readValue(_result, new TypeReference<List<Poem>>() {
            });
            return _infoList;
        } catch (Exception e) {
        }
        return null;
    }

    @Override
    public void unInit() {

    }
}
