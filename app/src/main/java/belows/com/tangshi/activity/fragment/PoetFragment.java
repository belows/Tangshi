package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yy.androidlib.util.sdk.BaseAdapter;

import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.AuthorInfo;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class PoetFragment extends BaseFragment implements TangshiCallback.Author {

    private ListView mListView;

    private BaseAdapter<AuthorInfo> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAuthorsAck(List<AuthorInfo> pInfoList) {
        mAdapter.setItems(pInfoList);
    }

    @Override
    protected View customView(LayoutInflater pInflater) {
        mListView = (ListView)pInflater.inflate(R.layout.listview,null);
        AppModel.INSTANCE.tangshi().queryAuthor();
        initAdapter();
        return mListView;
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter<AuthorInfo>() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(parent.getContext());
                }
                TextView _textView = (TextView) convertView;
                AuthorInfo _info = getItem(position);
                _textView.setText(_info.name + _info.worksCount);
                return _textView;
            }
        };
        mListView.setAdapter(mAdapter);
    }
}
