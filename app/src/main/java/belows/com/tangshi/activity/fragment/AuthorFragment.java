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
import belows.com.tangshi.domain.AuthorInfo;
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
        return _root;
    }

    @Override
    public void onAuthorsAck(List<AuthorInfo> pInfoList) {
        mAdapter.setItems(pInfoList);
    }

    @Override
    protected View customListItemView(AuthorInfo item, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
        }
        TextView _textView = (TextView) convertView;
        _textView.setText(item.name + item.worksCount);
        return _textView;
    }
}
