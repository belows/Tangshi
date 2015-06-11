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
import java.util.List;

import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.repository.AuthorRepository;
import belows.com.tangshi.repository.TangshiRepository;
import belows.com.tangshi.domain.AuthorInfo;
import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.utils.Preference;

/**
 * Created by belows on 15/6/11.
 */

public class TangshiModel extends Model {

    private AuthorRepository mAuthorRepository;
    private TangshiRepository mTangshiRepository;

    private List<Poem> mPoemList;

    public void setConnectionSource(ConnectionSource pConnectionSource) {
        mAuthorRepository = new AuthorRepository(pConnectionSource);
        mTangshiRepository = new TangshiRepository(pConnectionSource);
    }

    @Override
    public void init(Application pApp, Handler pIoHandler) {
        super.init(pApp, pIoHandler);
        mAuthorRepository.init();
        mTangshiRepository.init();
        initData();
    }

    public void queryAuthor() {
        runInIoHandler(new Runnable() {
            @Override
            public void run() {
                List<AuthorInfo> _infoList = mAuthorRepository.queryAll();
                for (AuthorInfo _info : _infoList) {
                    _info.worksCount = (int) mTangshiRepository.size(_info.name);
                }
                NotificationCenter.INSTANCE.getObserver(TangshiCallback.Author.class).onAuthorsAck(_infoList);
            }
        });
    }

    public void queryTangshi() {
        if (mPoemList == null || mPoemList.size() == 0) {
            mPoemList = mTangshiRepository.queryAll();
        }
        NotificationCenter.INSTANCE.getObserver(TangshiCallback.Tangshi.class).onTangshiAck(mPoemList);
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
                    mPoemList = getTangshiFromFile();
                    mAuthorRepository.save(getAuthorFromFile());
                    mTangshiRepository.save(mPoemList);
                    NotificationCenter.INSTANCE.getObserver(TangshiCallback.Tangshi.class).onTangshiAck(mPoemList);
                    Preference.save(getApp(), Preference.CommonKey.TANGSHI_INITED, true);
                }
            });
        }
    }

    private List<AuthorInfo> getAuthorFromFile() {
        try {
            InputStream _inputStream = getApp().getAssets().open("author.txt");
            InputStreamReader _isr = new InputStreamReader(_inputStream, "gb2312");
            BufferedReader _br = new BufferedReader(_isr);
            String _line = "";
            String _result = "";
            while ((_line = _br.readLine()) != null) {
                _result += _line;
            }

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
            InputStream _inputStream = getApp().getAssets().open("tangshi.txt");
            InputStreamReader _isr = new InputStreamReader(_inputStream, "gb2312");
            BufferedReader _br = new BufferedReader(_isr);
            String _line = "";
            String _result = "";
            while ((_line = _br.readLine()) != null) {
                _result += _line;
            }

            ObjectMapper _objectMapper = new ObjectMapper();
            List<Poem> _infoList = _objectMapper.readValue(_result, new TypeReference<List<Poem>>() {
            });
            return _infoList;
        } catch (Exception e) {
        }
        return null;
    }

    private <T> List<T> getAssetsFromFile(String pFileName, final Class<T> pClass) {
        try {
            InputStream _inputStream = getApp().getAssets().open(pFileName);
            InputStreamReader _isr = new InputStreamReader(_inputStream, "gb2312");
            BufferedReader _br = new BufferedReader(_isr);
            String _line = "";
            String _result = "";
            while ((_line = _br.readLine()) != null) {
                _result += _line;
            }

            ObjectMapper _objectMapper = new ObjectMapper();
            List<T> _infoList = _objectMapper.readValue(_result, new TypeReference<List<T>>() {
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
