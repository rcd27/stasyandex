package rcd27.github.com.stasyandex.view;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.model.Const;

public class LanguagesActivity extends AppCompatActivity {

    @Bind(R.id.ib_closeAvailableLanguages)
    ImageButton closeButton;

    @Bind(R.id.languagesListView)
    ListView languagesListView;

    @Bind(R.id.tv_available_languages_title)
    TextView tvTitle;

    private int direction;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_languages);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        if (intent.hasExtra("direction")) {
            direction = intent.getIntExtra("direction", 0);
            setHeadText(direction);
        }

        SharedPreferences prefs = getSharedPreferences(Const.TRANSLATION_CACHE, MODE_PRIVATE);
        Map<String, String> availableLanguagesMap = (Map<String, String>) prefs.getAll();

        List<String> languageListToShow = new ArrayList<>();
        for (String s : availableLanguagesMap.values()) {
            languageListToShow.add(s);
        }
        Collections.sort(languageListToShow);

        languagesListView.setOnItemClickListener((parent, view, position, id) ->
                closeActivity(languageListToShow.get(position)));


        languagesListView.setAdapter(new ArrayAdapter<>(getApplicationContext(),
                R.layout.item_language, languageListToShow));
    }

    @OnClick(R.id.ib_closeAvailableLanguages)
    public void onCloseButtonClick() {
        closeActivity("");
    }

    private void closeActivity(final String chosenLanguage) {
        Intent intent = new Intent();
        intent.putExtra("selectedLanguage", chosenLanguage);
        intent.putExtra("direction", direction);
        setResult(RESULT_OK, intent);
        finish();
    }

    private void setHeadText(int direction) {
        if (direction == Const.DIRECTION_FROM) {
            tvTitle.setText(R.string.translate_from);
        } else if (direction == Const.DIRECTION_TO) {
            tvTitle.setText(R.string.translate_to);
        }
    }
}
