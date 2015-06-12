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
import belows.com.tangshi.domain.Category;
import belows.com.tangshi.model.AppModel;

/**
 * Created by belows on 15/6/10.
 */
public class PoemCategoryFragment extends FrameFragment<Category> implements TangshiCallback.Category {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = super.onCreateView(inflater, container, savedInstanceState);
        AppModel.INSTANCE.tangshi().queryCategory();

        setTitle(getString(R.string.poem_category));
        return _root;
    }

    @Override
    public void onCategoryAck(List<Category> pCategoryList) {
        mAdapter.setItems(pCategoryList);
    }

    @Override
    protected View customListItemView(Category item, int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = new TextView(parent.getContext());
        }
        TextView _textView = (TextView) convertView;
        _textView.setText(item.mCategory + item.mWorksCount);
        return _textView;
    }
}
