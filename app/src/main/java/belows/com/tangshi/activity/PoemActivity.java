package belows.com.tangshi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;

import java.util.List;

import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.model.AppModel;
import belows.com.tangshi.widget.PoemView;

/**
 * Created by belows on 15/6/17.
 */
public class PoemActivity extends FrameActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Poem> _poemList = AppModel.INSTANCE.tangshi().getShowingPoemList();
        if (_poemList != null && _poemList.size() > 0) {
            PoemView _poemView = new PoemView(this);
            _poemView.update(_poemList.get(0));
            setContentView(_poemView);
        }
        setTitle("唐诗赏析");
    }

    @Override
    protected boolean showBack() {
        return false;
    }
}
