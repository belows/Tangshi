package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ListView;
import android.widget.TextView;

import com.yy.androidlib.util.sdk.BaseAdapter;

import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.MingJu;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class MingJuFragment extends BaseFragment implements TangshiCallback.IMingJu {
    private ListView mListView;
    private BaseAdapter<MingJu> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onMingJuAck(List<MingJu> pMingJuList) {
        mAdapter.setItems(pMingJuList);
    }

    @Override
    protected View customView(LayoutInflater pInflater) {
        mListView = (ListView) pInflater.inflate(R.layout.listview, null);
        initAdapter();
        AppModel.INSTANCE.tangshi().queryMingJu();

        setTitle(getString(R.string.ming_ju));
        return mListView;
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter<MingJu>() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MingJu _mingJu = getItem(position);
                if (convertView == null) {
                    convertView = (TextView) new TextView(parent.getContext());
                }
                TextView _textView = (TextView) convertView;
                _textView.setText(_mingJu.mAuthor + " " + _mingJu.mPoemTitle + " " + _mingJu.mContent);
                return _textView;
            }
        };
        mListView.setAdapter(mAdapter);
    }
}
