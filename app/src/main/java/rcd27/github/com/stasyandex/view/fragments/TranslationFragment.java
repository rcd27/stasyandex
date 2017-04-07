package rcd27.github.com.stasyandex.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.model.data.Translation;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.presenter.TranslatePresenter;
import rcd27.github.com.stasyandex.view.adapters.TranslationListAdapter;

import static rcd27.github.com.stasyandex.Constant.METHOD_INVOCATION;

public class TranslationFragment extends BaseFragment implements TranslationView {

    private final String TAG = getClass().getSimpleName();

    @Bind(R.id.editText)
    EditText editText;

    @Bind(R.id.bt_getTranslation)
    Button getTranslationButton;

    @Bind(R.id.recycler_view)
    RecyclerView tranclationRecyclerView;

    private TranslationListAdapter adapter;

    private TranslatePresenter presenter = new TranslatePresenter(this);

    //TODO тут какой-то кастомный ActivityCallback. Для чего?

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.translate_layout, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        tranclationRecyclerView.setLayoutManager(llm);
        adapter = new TranslationListAdapter(new ArrayList<Translation>(), presenter);
        tranclationRecyclerView.setAdapter(adapter);

        getTranslationButton.setOnClickListener(v -> presenter.onGetTranslation());

        presenter.onCreate(savedInstanceState);

        return view;
    }

    private void makeToast(String text) {
        Snackbar.make(tranclationRecyclerView, text, Snackbar.LENGTH_LONG).show();
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
    public void showTranslation(List<Translation> translationsToShow) {
        Log.i(TAG, METHOD_INVOCATION + "showTranslation()");
        adapter.setTranslationList(translationsToShow);
    }

    @Override
    protected BasePresenter getPresenter() {
        Log.i(TAG, METHOD_INVOCATION + "getPresenter()");
        return presenter;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onSaveInstanceState(outState);
    }
}
