package rcd27.github.com.stasyandex.view.translation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnTextChanged;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.di.DaggerTranslationComponent;
import rcd27.github.com.stasyandex.di.TranslationComponent;
import rcd27.github.com.stasyandex.di.TranslationModule;
import rcd27.github.com.stasyandex.model.translation.dto.Translation;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.presenter.translation.TranslationPresenter;
import rcd27.github.com.stasyandex.view.BaseFragment;
import rcd27.github.com.stasyandex.view.LanguagesActivity;
import rcd27.github.com.stasyandex.view.history.HistoryActivity;

public class TranslationFragment extends BaseFragment implements TranslationView {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.tv_languageFrom)
    TextView tvLanguageFrom;

    @BindView(R.id.ib_switch_direction)
    ImageButton ibSwitchDirection;

    @BindView(R.id.tv_languageTo)
    TextView tvLanguageTo;

    @BindView(R.id.translation_editText)
    EditText editText;

    @BindView(R.id.bt_clearEditText)
    ImageButton btClearEditText;

    @BindView(R.id.tv_translation_result)
    TextView tvTranslationResult;

    @BindView(R.id.bt_history)
    ImageButton historyButton;

    @Inject
    TranslationPresenter presenter;

    private TranslateButtonListener listener;

    //TODO StartAndroid: объясняетя как такого избежать.
    private TranslationComponent component;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (TranslateButtonListener) context;
            Log.i(TAG, "onAttach: listener attached!");
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement TranslateButtonListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == component) {
            component = DaggerTranslationComponent.builder()
                    .translationModule(new TranslationModule(this, listener,
                            getContext().getApplicationContext()))
                    .build();
        }
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_translation, container, false);
        ButterKnife.bind(this, view);

        /*ДЛЯ ДЕФОЛТА*/
        showLanguageFrom("русский");
        showLanguageTo("сербский");

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        presenter.handleIntentForSelectedLanguages(data);
    }

    @OnClick(R.id.tv_languageFrom)
    public void onClickLanguageFrom() {
        presenter.onChooseLanguage(1);
    }

    @OnClick(R.id.tv_languageTo)
    public void onClickLanguageTo() {
        presenter.onChooseLanguage(2);
    }

    @OnClick(R.id.bt_clearEditText)
    public void onClearEditTextButtonClicked() {
        presenter.clearTranslationEditText();
    }

    @OnClick(R.id.bt_history)
    public void onHistoryButtonClicked() {
        presenter.switchToHistory();
    }

    @OnTextChanged(R.id.translation_editText)
    public void onTextChanged() {
        presenter.getTranslationForTextFromEditText();
        listener.onTranslateEditTextChanged(presenter.getDirection(), getTextFromEditText());
    }

    @Override
    public void showError(String error) {
        makeToast(tvTranslationResult, error);
    }


    @Override
    public String getTextFromEditText() {
        return editText.getText().toString();
    }

    @Override
    public void showTranslation(Translation resultTranslation) {
        tvTranslationResult.setText(resultTranslation.show());
    }

    @Override
    public void showEmpty() {
        tvTranslationResult.setText("");
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void chooseLanguage(final int direction) {
        Intent intent = new Intent(getContext(), LanguagesActivity.class);
        intent.putExtra("direction", direction);
        startActivityForResult(intent, 0);
    }

    @Override
    public void openHistory() {
        Intent intent = new Intent(getContext(), HistoryActivity.class);
        startActivity(intent);
    }

    @Override
    public void clearEditText() {
        editText.setText("");
    }

    @Override
    public void showLanguageFrom(String selectedLanguage) {
        tvLanguageFrom.setText(selectedLanguage);
    }

    @Override
    public void showLanguageTo(String selectedLanguage) {
        tvLanguageTo.setText(selectedLanguage);
    }

    @Override
    public String getLanguageFrom() {
        return tvLanguageFrom.getText().toString();
    }

    @Override
    public String getLanguageTo() {
        return tvLanguageTo.getText().toString();
    }

    public interface TranslateButtonListener {
        void onTranslateEditTextChanged(String direction, String textFromEditText);
    }
}
