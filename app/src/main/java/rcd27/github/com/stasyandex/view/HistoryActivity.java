package rcd27.github.com.stasyandex.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageButton;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rcd27.github.com.stasyandex.R;

public class HistoryActivity extends AppCompatActivity {

    @Bind(R.id.ib_closeHistory)
    ImageButton btCloseHistory;

    @Bind(R.id.et_historyEditText)
    EditText editText;

    @Bind(R.id.ib_deleteHistory)
    ImageButton btDeleteHistory;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.ib_closeHistory)
    public void onCloseButtonClick() {
        finish();
    }
}
