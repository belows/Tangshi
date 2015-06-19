package belows.com.tangshi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;

import belows.com.tangshi.R;
import belows.com.tangshi.activity.fragment.PoemListFragment;
import belows.com.tangshi.domain.PoemQueryType;

/**
 * Created by belows on 15/6/19.
 */
public class PoemListActivity extends BaseFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poem_list);

        String _queryKey = getIntent().getStringExtra(PoemListFragment.QUERY_KEY);
        int _queryType = getIntent().getIntExtra(PoemListFragment.QUERY_TYPE, PoemQueryType.ALL.ordinal());

        PoemListFragment _poemListFragment = new PoemListFragment();
        Bundle _data = new Bundle();
        _data.putString(PoemListFragment.QUERY_KEY, _queryKey);
        _data.putInt(PoemListFragment.QUERY_TYPE, _queryType);

        _poemListFragment.setArguments(_data);
        FragmentTransaction _transaction = getSupportFragmentManager().beginTransaction();
        _transaction.add(R.id.fl_poem_list, _poemListFragment);
        _transaction.commit();
    }
}
