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
import belows.com.tangshi.domain.PlayList;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class PlayListFragment extends FrameFragment<PlayList> implements TangshiCallback.IPlayList {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = super.onCreateView(inflater, container, savedInstanceState);

        AppModel.INSTANCE.tangshi().queryPlayList();
        setTitle(getString(R.string.play_list));
        return _root;
    }

    @Override
    public void onPlayListAck(List<PlayList> pPlayList) {
        mAdapter.setItems(pPlayList);
    }

    @Override
    protected View customListItemView(PlayList item, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
        }
        TextView _textView = (TextView) convertView;
        _textView.setText(item.mAuthor + " " + item.mPoemTitle);
        return _textView;
    }
}
