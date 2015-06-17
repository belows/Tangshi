package belows.com.tangshi.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import belows.com.tangshi.R;
import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.PoemQueryType;
import belows.com.tangshi.model.AppModel;
import belows.com.tangshi.domain.Poem;

/**
 * Created by belows on 15/6/10.
 */
public class MainFragment extends FrameFragment<Poem> {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View _root = inflater.inflate(R.layout.fragment_main, container, false);
        FragmentTransaction _transaction = getActivity().getSupportFragmentManager().beginTransaction();
        PoemListFragment _fragment = new PoemListFragment();
        Bundle _bundle = new Bundle();
        _bundle.putInt(PoemListFragment.QUERY_TYPE, PoemQueryType.ALL.ordinal());
        _fragment.setArguments(_bundle);
        _transaction.add(R.id.fl_main, _fragment);
        _transaction.commit();
        return _root;
    }
}
