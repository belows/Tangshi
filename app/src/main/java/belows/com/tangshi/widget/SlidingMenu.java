package belows.com.tangshi.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yy.androidlib.util.sdk.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.activity.fragment.CollectionFragment;
import belows.com.tangshi.activity.fragment.MingJuFragment;
import belows.com.tangshi.activity.fragment.MainFragment;
import belows.com.tangshi.activity.fragment.PlayListFragment;
import belows.com.tangshi.activity.fragment.PoemCategoryFragment;
import belows.com.tangshi.activity.fragment.PoetFragment;
import belows.com.tangshi.activity.fragment.SettingFragment;
import belows.com.tangshi.domain.MenuItem;

/**
 * Created by belows on 15/6/10.
 */
public class SlidingMenu extends LinearLayout {

    private List<MenuItem> mItemList;

    private TextView mUserView;
    private ListView mMenuListView;

    private OnMenuClickListener mMenuClickListener;
    private Context mContext;
    private BaseAdapter<MenuItem> mAdapter;

    public interface OnMenuClickListener {
        void onMenuClick(MenuItem pItem);
    }

    public void setMenuClickListener(OnMenuClickListener pMenuClickListener) {
        mMenuClickListener = pMenuClickListener;
    }

    public SlidingMenu(Context context) {
        this(context, null);
    }

    public SlidingMenu(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        inflate(context, R.layout.layout_sliding_menu, this);

        initVariables();
        initViews();
        initAdapter();
        bindData();
        initListeners();
    }

    public void click(int index) {
        if (mMenuClickListener != null) {
            mMenuClickListener.onMenuClick(mAdapter.getItem(index));
        }
    }

    private void initVariables() {
        mItemList = new ArrayList<MenuItem>();
        mItemList.add(new MenuItem(getString(R.string.home), R.drawable.icon_menu1, MainFragment.class));
        mItemList.add(new MenuItem(getString(R.string.poem_category), R.drawable.icon_menu1, PoemCategoryFragment.class));
        mItemList.add(new MenuItem(getString(R.string.poet), R.drawable.icon_menu1, PoetFragment.class));
        mItemList.add(new MenuItem(getString(R.string.ming_ju), R.drawable.icon_menu1, MingJuFragment.class));
        mItemList.add(new MenuItem(getString(R.string.collection), R.drawable.icon_menu1, CollectionFragment.class));
        mItemList.add(new MenuItem(getString(R.string.play_list), R.drawable.icon_menu1, PlayListFragment.class));
        mItemList.add(new MenuItem(getString(R.string.setting), R.drawable.icon_menu1, SettingFragment.class));
    }

    private void initViews() {
        mUserView = (TextView) findViewById(R.id.view_user);
        mMenuListView = (ListView) findViewById(R.id.lv_list);

        mMenuListView.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    private void initAdapter() {
        mAdapter = new BaseAdapter<MenuItem>() {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                MenuItem _item = mAdapter.getItem(position);
                if (convertView == null) {
                    convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sliding_menu, null);
                }
                TextView _textView = (TextView) convertView.findViewById(R.id.tv_item_name);
                _textView.setText(_item.mItemName);
                ImageView _imageView = (ImageView) convertView.findViewById(R.id.iv_icon);
                _imageView.setImageResource(_item.mDrawableId);
                return convertView;
            }
        };
        mAdapter.setItems(mItemList);
    }

    private void bindData() {
        mMenuListView.setAdapter(mAdapter);
    }

    private void initListeners() {
        mMenuListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                click(position);
            }
        });
    }

    private String getString(int pStrId) {
        return mContext.getString(pStrId);
    }
}
