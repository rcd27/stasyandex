package rcd27.github.com.stasyandex.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import rcd27.github.com.stasyandex.presenter.TranslatePresenter;
import rcd27.github.com.stasyandex.view.adapters.TranslationListAdapter;

public class TranslationFragment extends BaseFragment implements TranslationView {

    @Bind(R.id.editText)
    EditText editText;

    @Bind(R.id.bt_getTranslation)
    Button getTranslationButton;

    @Bind(R.id.tv_translation_result)
    TextView translationResultTextView;

    private TranslatePresenter presenter = new TranslatePresenter(this);

    //TODO тут какой-то кастомный ActivityCallback. Для чего?

    public static TranslationFragment newInstance() {
        return new TranslationFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translate_layout, container, false);
        ButterKnife.bind(this, view);

        /*
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        tranclationRecyclerView.setLayoutManager(llm);
        adapter = new TranslationListAdapter(new ArrayList<>(), presenter);
        tranclationRecyclerView.setAdapter(adapter);
        */

        getTranslationButton.setOnClickListener(v -> presenter.onGetTranslation());

        presenter.onCreate(savedInstanceState);

        return view;
    }

    private void makeToast(String text) {
        /*
        Snackbar.make(tranclationRecyclerView, word, Snackbar.LENGTH_LONG).show();
        */
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }


    @Override
    public String getTranslationFor() {
        return editText.getText().toString();
    }

    @Override
    public void showTranslation(List<String> translationsToShow) {
        /*
        adapter.setTranslationList(translationsToShow);
        */
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
