package belows.com.tangshi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.List;

import belows.com.tangshi.callbacks.TangshiCallback;
import belows.com.tangshi.domain.Poem;
import belows.com.tangshi.domain.Verse;
import belows.com.tangshi.model.AppModel;
import belows.com.tangshi.utils.ToastUtil;

/**
 * Created by belows on 15/6/17.
 */
public class PoemActivity extends FrameActivity {

    public static final String POEM = "poem";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Poem _poem = (Poem) getIntent().getSerializableExtra(POEM);
        showToast(_poem.mTitle.mContent);

        setTitle("唐诗赏析");
    }

    @Override
    protected boolean showBack() {
        return false;
    }
}
