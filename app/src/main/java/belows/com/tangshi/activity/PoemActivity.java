package belows.com.tangshi.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.model.AppModel;
import belows.com.tangshi.widget.PoemView;

/**
 * Created by belows on 15/6/17.
 */
public class PoemActivity extends FrameActivity {

    public static final String CURRENT_ITEM = "current_item";

    private int mCurrentItem;
    private List<Poem> mPoemList;
    private ViewPager mViewPager;

    private PagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_detail);
        setTitle(R.string.poem_title);
        initVariables();
        initViews();
        initAdapters();
        bindData();
    }

    private void initVariables() {
        mCurrentItem = getIntent().getIntExtra(CURRENT_ITEM, 0);
        mPoemList = AppModel.INSTANCE.tangshi().getShowingPoemList();
    }

    private void initViews() {
        mViewPager = (ViewPager) findViewById(R.id.vp_poem);
    }

    private void initAdapters() {
        mAdapter = new PagerAdapter() {
            @Override
            public int getCount() {
                if (mPoemList == null) {
                    return 0;
                }
                return mPoemList.size();
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                PoemView _poemView = new PoemView(container.getContext());
                _poemView.update(mPoemList.get(position));
                container.addView(_poemView);
                return _poemView;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                (container).removeView((View) object);
            }
        };
    }

    private void bindData() {
        mViewPager.setAdapter(mAdapter);
        mViewPager.setCurrentItem(mCurrentItem);
    }

    @Override
    protected boolean showBack() {
        return false;
    }
}
