package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.domain.PoemQueryType;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/17.
 */
public class PoemListFragment extends FrameFragment<Poem> implements TangshiCallback.Tangshi {
    public static final String QUERY_TYPE = "query_type";
    public static final String QUERY_KEY = "query_key";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = super.onCreateView(inflater, container, savedInstanceState);
        int _queryType = getArguments().getInt(QUERY_TYPE, PoemQueryType.ALL.ordinal());
        if (_queryType == PoemQueryType.ALL.ordinal()) {
            AppModel.INSTANCE.tangshi().queryTangshi();
            setTitle(getString(R.string.tangshi));
        } else if (_queryType == PoemQueryType.AUTHOR.ordinal()) {
            String _author = getArguments().getString(QUERY_KEY, "");
            AppModel.INSTANCE.tangshi().queryAuthorTangshi(_author);
            setTitle(_author);
        } else {
            String _category = getArguments().getString(QUERY_KEY, "");
            AppModel.INSTANCE.tangshi().queryCategoryTangshi(_category);
            setTitle(_category);
        }

        return _root;
    }

    @Override
    public void onTangshiAck(List<Poem> pTangshiList) {
        mAdapter.setItems(pTangshiList);
    }

    @Override
    protected View customListItemView(Poem item, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
        }
        TextView _textView = (TextView) convertView;
        _textView.setText(item.mAuthorName + item.mTitle.mContent);
        return _textView;
    }
}
