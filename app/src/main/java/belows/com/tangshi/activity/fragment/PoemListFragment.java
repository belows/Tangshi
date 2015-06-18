package belows.com.tangshi.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.activity.PoemActivity;
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

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(PoemActivity.class);
            }
        });
        return _root;
    }

    @Override
    public void onTangshiAck(List<Poem> pTangshiList) {
        AppModel.INSTANCE.tangshi().setShowingPoemList(pTangshiList);
        mAdapter.setItems(pTangshiList);
    }

    @Override
    protected View customListItemView(Poem item, int position, View convertView, ViewGroup parent) {
        ViewHolder _holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_poem, null);
            _holder = new ViewHolder();
            _holder.mTitleTextView = (TextView) convertView.findViewById(R.id.tv_title);
            _holder.mAuthorTextView = (TextView) convertView.findViewById(R.id.tv_author);
            _holder.mCollectionImageView = (ImageView) convertView.findViewById(R.id.iv_collection);
            _holder.mMoreTextView = (TextView) convertView.findViewById(R.id.tv_more);
            convertView.setTag(_holder);
        } else {
            _holder = (ViewHolder) convertView.getTag();
        }
        _holder.mTitleTextView.setText(item.mTitle.mContent);
        _holder.mAuthorTextView.setText(item.mAuthorName);
        return convertView;
    }

    private static class ViewHolder {
        TextView mTitleTextView;
        ImageView mCollectionImageView;
        TextView mAuthorTextView;
        TextView mMoreTextView;
    }
}
