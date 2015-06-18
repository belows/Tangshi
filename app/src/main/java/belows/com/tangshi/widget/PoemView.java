package belows.com.tangshi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import belows.com.tangshi.R;
import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.domain.Verse;

/**
 * Created by belows on 15/6/18.
 */
public class PoemView extends LinearLayout {

    private VerseView mTitleView;
    private VerseView mAuthorView;
    private LinearLayout mContentView;
    private ExplainView mExplainView1;
    private ExplainView mExplainView2;
    private ExplainView mExplainView3;

    private Poem mPoem;

    public PoemView(Context context) {
        this(context, null);
    }

    public PoemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        inflate(context, R.layout.layout_poem_detail, this);

        initViews();
    }

    public void update(Poem pPoem) {
        mPoem = pPoem;
        mTitleView.update(pPoem.mTitle);
        mAuthorView.update(pPoem.mAuthor);
        for (Verse _content : pPoem.mContentList) {
            VerseView _verseView = (VerseView) LayoutInflater.from(getContext()).inflate(R.layout.item_poem_content, null);
            _verseView.update(_content);
            mContentView.addView(_verseView);
        }
        mExplainView1.update("注解", pPoem.mAppreciationList);
        mExplainView2.update("韵译", pPoem.mRhymeList);
        mExplainView3.update("评析", pPoem.mCommentList);
    }

    private void initViews() {
        mTitleView = (VerseView) findViewById(R.id.vv_title);
        mAuthorView = (VerseView) findViewById(R.id.vv_author);
        mContentView = (LinearLayout) findViewById(R.id.ll_content);
        mExplainView1 = (ExplainView) findViewById(R.id.ev_1);
        mExplainView2 = (ExplainView) findViewById(R.id.ev_2);
        mExplainView3 = (ExplainView) findViewById(R.id.ev_3);
    }
}
