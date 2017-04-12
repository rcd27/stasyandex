package rcd27.github.com.stasyandex.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.presenter.DictionaryPresenter;
import rcd27.github.com.stasyandex.presenter.visualobjects.DictionaryDefinition;
import rcd27.github.com.stasyandex.presenter.visualobjects.DictionaryItem;
import rcd27.github.com.stasyandex.view.adapters.DictionaryAdapter;

public class DictionaryFragment extends BaseFragment implements DictionaryView {

    /*Здесь отображается слово, с которого был произведён перевод*/
    @Bind(R.id.tv_dictionary_word)
    TextView dictionaryOriginWord;

    /*Часть речи исходного слова*/
    @Bind(R.id.tv_dictionary_pos)
    TextView dictionaryOriginWordPos;

    /*Для отображения вариантов перевода*/
    @Bind(R.id.dictionary_recycler_view)
    RecyclerView definitionItemsRecyclerView;

    private DictionaryAdapter dictionaryAdapter;

    private DictionaryPresenter presenter = new DictionaryPresenter(this);

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dictionary_layout, container, false);
        ButterKnife.bind(this,view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        definitionItemsRecyclerView.setLayoutManager(llm);
        dictionaryAdapter = new DictionaryAdapter(new ArrayList<>(), presenter);
        definitionItemsRecyclerView.setAdapter(dictionaryAdapter);

        //TODO тут что-то ещё
        return view;
    }

    @Override
    public String getDictionaryFor() {
        return null;
    }

    @Override
    public void showDictionaryDefiniton(List<DictionaryDefinition> definitions) {

    }

    @Override
    public void showDictionaryDictionaryItems(List<DictionaryItem> items) {
        dictionaryAdapter.setDictionaryItemList(items);
    }

    @Override
    public void showEmpty() {

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }
}
