package belows.com.tangshi.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.AsyncTask;
import android.support.v4.widget.SlidingPaneLayout;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.yy.androidlib.util.sdk.DimensionUtil;

import belows.com.tangshi.R;
import belows.com.tangshi.domain.MenuItem;

/**
 * Created by belows on 15/6/10.
 */
public class SlidingLayout extends SlidingPaneLayout {
    private int mSlidingWidth;
    private int mMenuId;
    private int mContentId;

    private SlidingMenu mSlidingMenu;
    private ViewGroup mSlidingContent;

    public SlidingLayout(Context context) {
        this(context, null);
    }

    public SlidingLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        initVariables(context, attrs);
    }

    public void toggle() {
        if (isOpen()) {
            closePane();
        } else {
            openPane();
        }
    }

    public void setMenuClickListener(SlidingMenu.OnMenuClickListener pMenuClickListener) {
        mSlidingMenu.setMenuClickListener(pMenuClickListener);
    }

    private void initVariables(Context pContext, AttributeSet pAttrs) {

        TypedArray _typedArray = pContext.obtainStyledAttributes(pAttrs, R.styleable.SlidingLayout);

        int _defaultSlidingWidth = DimensionUtil.getScreenWidth(pContext) * 2 / 3;
        mSlidingWidth = (int) _typedArray.getDimension(R.styleable.SlidingLayout_sliding_width, _defaultSlidingWidth);

        mMenuId = _typedArray.getResourceId(R.styleable.SlidingLayout_menu_id, 0);
        if (mMenuId == 0) {
            throw new IllegalArgumentException("sliding layout must contain menu id");
        }

        mContentId = _typedArray.getResourceId(R.styleable.SlidingLayout_content_id, 0);
        if (mContentId == 0) {
            throw new IllegalArgumentException("sliding layout must contain content id");
        }

        _typedArray.recycle();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mSlidingMenu = (SlidingMenu) findViewById(mMenuId);
        if (mSlidingMenu == null) {
            throw new IllegalArgumentException("sliding menu is null");
        }
        ViewGroup.LayoutParams _lp = mSlidingMenu.getLayoutParams();
        _lp.width = mSlidingWidth;
        mSlidingMenu.setLayoutParams(_lp);

        mSlidingContent = (ViewGroup) findViewById(mContentId);
        if (mSlidingContent == null) {
            throw new IllegalArgumentException("sliding content is null");
        }

        initListeners();
    }

    private void initListeners() {
        OnTouchListener _onTouchListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isOpen()) {
                    closePane();
                    return true;
                }
                return false;
            }
        };
        mSlidingMenu.setOnTouchListener(_onTouchListener);
        mSlidingContent.setOnTouchListener(_onTouchListener);
    }
}
