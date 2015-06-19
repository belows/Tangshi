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
import belows.com.tangshi.activity.PoemListActivity;
import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.AuthorInfo;
import belows.com.tangshi.domain.Category;
import belows.com.tangshi.domain.PoemQueryType;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class AuthorFragment extends FrameFragment<AuthorInfo> implements TangshiCallback.Author {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = super.onCreateView(inflater, container, savedInstanceState);
        AppModel.INSTANCE.tangshi().queryAuthor();

        setTitle(getString(R.string.poet_list));

        initListeners();
        return _root;
    }

    @Override
    public void onAuthorsAck(List<AuthorInfo> pInfoList) {
        mAdapter.setItems(pInfoList);
    }

    @Override
    protected View customListItemView(AuthorInfo item, int position, View convertView, ViewGroup parent) {
        ViewHolder _holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_author_list, null);
            _holder = new ViewHolder();
            _holder.mContentView = (TextView) convertView.findViewById(R.id.tv_content);
            _holder.mNumberView = (TextView) convertView.findViewById(R.id.tv_num);
            _holder.mIconView = (ImageView) convertView.findViewById(R.id.iv_icon);
            convertView.setTag(_holder);
        } else {
            _holder = (ViewHolder) convertView.getTag();
        }
        _holder.mContentView.setText(item.name);
        _holder.mNumberView.setText(item.worksCount + "");
        _holder.mIconView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;
    }

    private void initListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AuthorInfo _authorInfo = mAdapter.getItem(position);
                Intent _intent = new Intent(getActivity(), PoemListActivity.class);
                _intent.putExtra(PoemListFragment.QUERY_TYPE, PoemQueryType.AUTHOR.ordinal());
                _intent.putExtra(PoemListFragment.QUERY_KEY, _authorInfo.name);
                startActivity(_intent);
            }
        });
    }

    private static class ViewHolder {
        TextView mContentView;
        TextView mNumberView;
        ImageView mIconView;
    }
}
