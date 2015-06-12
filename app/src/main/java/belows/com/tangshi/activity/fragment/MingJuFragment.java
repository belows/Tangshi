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
import belows.com.tangshi.domain.MingJu;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class MingJuFragment extends FrameFragment<MingJu> implements TangshiCallback.IMingJu {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root =  super.onCreateView(inflater, container, savedInstanceState);
        AppModel.INSTANCE.tangshi().queryMingJu();

        setTitle(getString(R.string.ming_ju));
        return _root;
    }

    @Override
    public void onMingJuAck(List<MingJu> pMingJuList) {
        mAdapter.setItems(pMingJuList);
    }

    @Override
    protected View customListItemView(MingJu item, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = (TextView) new TextView(parent.getContext());
        }
        TextView _textView = (TextView) convertView;
        _textView.setText(item.mAuthor + " " + item.mPoemTitle + " " + item.mContent);
        return _textView;
    }
}
