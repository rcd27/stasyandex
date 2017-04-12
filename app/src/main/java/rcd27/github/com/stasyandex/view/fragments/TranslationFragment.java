package rcd27.github.com.stasyandex.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.presenter.TranslationPresenter;

public class TranslationFragment extends BaseFragment implements TranslationView {

    @Bind(R.id.translation_editText)
    EditText editText;

    @Bind(R.id.bt_getTranslation)
    Button getTranslationButton;

    @Bind(R.id.tv_translation_result)
    TextView translationResultTextView;

    private TranslationPresenter presenter = new TranslationPresenter(this);

    public static TranslationFragment newInstance() {
        return new TranslationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translation_layout, container, false);
        ButterKnife.bind(this, view);

        getTranslationButton.setOnClickListener(v -> presenter.onGetTranslation());

        presenter.onCreate(savedInstanceState);

        return view;
    }

    private void makeToast(String text) {
        Snackbar.make(translationResultTextView, text, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }


    @Override
    public String getTextFromEditText() {
        System.out.println("editText.getText().toString() = " + editText.getText().toString());
        return editText.getText().toString();
    }

    @Override
    public void showTranslation(List<String> translationsToShow) {
        translationResultTextView.setText(translationsToShow.toString());
    }

    @Override
    public void showEmpty() {
        translationResultTextView.setText("");
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
