package belows.com.tangshi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by belows on 15/6/18.
 */
public class ExplainView extends LinearLayout {

    private TextView mTitleTextView;
    private TextView mExplainTextView;

    private float mTitleTextSize;
    private float mExplainTextSize;
    private int mTitleTextColor;
    private int mExplainTextColor;
    private int mTitleBgResId;

    public ExplainView(Context context) {
        this(context, null);
    }

    public ExplainView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOrientation(VERTICAL);

        mTitleTextView = new TextView(context);
        mExplainTextView = new TextView(context);

        addView(mTitleTextView);
        addView(mExplainTextView);
    }

    public void update(String pTitle, List<String> pExplainList) {
        mTitleTextView.setText(pTitle);

        String _explainText = "";
        for (String _explain : pExplainList) {
            _explainText += _explain + "\n";
        }
        mExplainTextView.setText(_explainText);
    }
}
