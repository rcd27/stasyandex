package com.github.rcd27.stasyandex.dictionary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.di.DaggerDictionaryComponent;
import com.github.rcd27.stasyandex.di.dictionary.DictionaryComponent;
import com.github.rcd27.stasyandex.di.dictionary.DictionaryModule;
import com.github.rcd27.stasyandex.BaseFragment;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryFragment extends BaseFragment implements DictionaryView {

    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.tv_dictionary_word) TextView dictionaryOriginWord;
    @BindView(R.id.tv_dictionary_pos) TextView dictionaryOriginWordPos;
    @BindView(R.id.dictionary_recycler_view) RecyclerView definitionItemsRecyclerView;

    @Inject DictionaryPresenter presenter;

    private DictionaryAdapter dictionaryAdapter;

    private DictionaryComponent component;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == component) {
            component = DaggerDictionaryComponent.builder()
                    .dictionaryModule(new DictionaryModule(this))
                    .build();
        }
        component.inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dictionary, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        definitionItemsRecyclerView.setLayoutManager(llm);
        dictionaryAdapter = new DictionaryAdapter(new ArrayList<>());
        definitionItemsRecyclerView.setAdapter(dictionaryAdapter);

        return view;
    }

    @Override
    public void showError(String text) {
        makeToast(definitionItemsRecyclerView, text);
    }

    @Override
    public void showDefinition(DictionaryVisualDefinition definition) {
        dictionaryOriginWord.setText(definition.getText());
        dictionaryOriginWordPos.setText(definition.getPos());
    }

    @Override
    public void showDictionaryItems(List<DictionaryVisualItem> items) {
        dictionaryAdapter.setDictionaryItemList(items);
    }

    @Override
    public void showEmpty() {
        dictionaryOriginWord.setText("");
        dictionaryOriginWordPos.setText("");
        dictionaryAdapter.setDictionaryItemList(Collections.EMPTY_LIST);
    }

    @Override
    public DictionaryPresenter getPresenter() {
        return presenter;
    }


}
