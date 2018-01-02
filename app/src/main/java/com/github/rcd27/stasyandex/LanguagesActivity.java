package com.github.rcd27.stasyandex;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.github.rcd27.stasyandex.common.Const;
import com.github.rcd27.stasyandex.data.translation.Translation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LanguagesActivity extends AppCompatActivity {

    @BindView(R.id.ib_closeAvailableLanguages) ImageButton closeButton;
    @BindView(R.id.languagesListView) ListView languagesListView;
    @BindView(R.id.tv_available_languages_title) TextView tvTitle;

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

        Map<String, String> availableLanguagesMap = Translation.createLanguagesMap();

        List<String> languageListToShow = new ArrayList<>();
        languageListToShow.addAll(availableLanguagesMap.values());
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
