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
import belows.com.tangshi.model.AppModel;
import belows.com.tangshi.domain.Poem;

/**
 * Created by belows on 15/6/10.
 */
public class MainFragment extends BaseFragment implements TangshiCallback.Tangshi {

    private ListView mListView;
    private BaseAdapter<Poem> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onTangshiAck(List<Poem> pTangshiList) {
        mAdapter.setItems(pTangshiList);
    }

    @Override
    protected View customView(LayoutInflater pInflater) {
        mListView = (ListView) pInflater.inflate(R.layout.fragment_main, null);
        initAdapter();

        AppModel.INSTANCE.tangshi().queryTangshi();
        setTitle(getString(R.string.tangshi));

        return mListView;
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter<Poem>() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                if (convertView == null) {
                    convertView = new TextView(parent.getContext());
                }
                Poem _poem = getItem(position);
                TextView _textView = (TextView) convertView;
                _textView.setText(_poem.mAuthorName + _poem.mTitle.mContent);
                return _textView;
            }
        };
        mListView.setAdapter(mAdapter);
    }
}
