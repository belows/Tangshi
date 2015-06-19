package belows.com.tangshi.activity.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.activity.PoemActivity;
import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.MingJu;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class MingJuFragment extends FrameFragment<MingJu> implements TangshiCallback.IMingJu {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = super.onCreateView(inflater, container, savedInstanceState);
        AppModel.INSTANCE.tangshi().queryMingJu();

        setTitle(getString(R.string.ming_ju));
        initListeners();
        return _root;
    }

    @Override
    public void onMingJuAck(List<MingJu> pMingJuList) {
        mAdapter.setItems(pMingJuList);
    }

    @Override
    protected View customListItemView(MingJu item, int position, View convertView, ViewGroup parent) {

        ViewHolder _holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ming_ju, null);
            _holder = new ViewHolder();
            _holder.mContentView = (TextView) convertView.findViewById(R.id.tv_content);
            _holder.mTitleView = (TextView) convertView.findViewById(R.id.tv_title);
            _holder.mAuthorView = (TextView) convertView.findViewById(R.id.tv_author);
            convertView.setTag(_holder);
        } else {
            _holder = (ViewHolder) convertView.getTag();
        }
        _holder.mContentView.setText(item.mContent);
        _holder.mTitleView.setText(item.mPoemTitle);
        _holder.mAuthorView.setText(item.mAuthor);
        return convertView;
    }

    private void initListeners() {
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MingJu _mingJu = mAdapter.getItem(position);
                AppModel.INSTANCE.tangshi().queryTangshi(_mingJu.mPoemTitle,_mingJu.mAuthor);
                Intent _intent = new Intent(getActivity(), PoemActivity.class);
                startActivity(_intent);
            }
        });
    }

    private static class ViewHolder {
        TextView mContentView;
        TextView mTitleView;
        TextView mAuthorView;
    }
}
