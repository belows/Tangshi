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
import belows.com.tangshi.domain.Category;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class PoemCategoryFragment extends BaseFragment implements TangshiCallback.Category {

    private ListView mListView;
    private BaseAdapter<Category> mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCategoryAck(List<Category> pCategoryList) {
        mAdapter.setItems(pCategoryList);
    }

    @Override
    protected View customView(LayoutInflater pInflater) {
        mListView = (ListView) pInflater.inflate(R.layout.listview, null);

        initAdapter();
        AppModel.INSTANCE.tangshi().queryCategory();

        setTitle(getString(R.string.poem_category));
        return mListView;
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter<Category>() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                Category _category = getItem(position);

                if (convertView == null) {
                    convertView = new TextView(parent.getContext());
                }
                TextView _textView = (TextView) convertView;
                _textView.setText(_category.mCategory + _category.mWorksCount);
                return _textView;
            }
        };
        mListView.setAdapter(mAdapter);
    }
}
