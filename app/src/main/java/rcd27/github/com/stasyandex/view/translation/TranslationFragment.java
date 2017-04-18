package rcd27.github.com.stasyandex.view.translation;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.presenter.translation.TranslationPresenter;
import rcd27.github.com.stasyandex.presenter.visualobject.Translation;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.BaseFragment;

public class TranslationFragment extends BaseFragment implements TranslationView {

    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.translation_editText)
    EditText editText;

    @Bind(R.id.bt_getTranslation)
    Button getTranslationButton;

    @Bind(R.id.tv_translation_result)
    TextView translationResultTextView;

    private TranslationPresenter presenter = new TranslationPresenter(this);

    private Listener listener;

    public static TranslationFragment newInstance() {
        return new TranslationFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (Listener) context;
            Log.i(TAG, "onAttach: listener attached!");
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    " must implement Listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translation_layout, container, false);
        ButterKnife.bind(this, view);

        getTranslationButton.setOnClickListener(v -> {
            presenter.onGetTranslation();
            listener.onTranslateButtonClicked(getTextFromEditText());
        });

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
        return editText.getText().toString();
    }

    @Override
    public void showTranslation(Translation resultTranslation) {
        translationResultTextView.setText(resultTranslation.show());
    }

    @Override
    public void showEmptyResut() {
        translationResultTextView.setText("");
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    public interface Listener {
        public void onTranslateButtonClicked(String textFromEditText);
    }
}
