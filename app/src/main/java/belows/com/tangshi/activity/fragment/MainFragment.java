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
import belows.com.tangshi.model.AppModel;
import belows.com.tangshi.domain.Poem;

/**
 * Created by belows on 15/6/10.
 */
public class MainFragment extends FrameFragment<Poem> implements TangshiCallback.Tangshi {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = super.onCreateView(inflater, container, savedInstanceState);
        AppModel.INSTANCE.tangshi().queryTangshi();

        setTitle(getString(R.string.tangshi));
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
