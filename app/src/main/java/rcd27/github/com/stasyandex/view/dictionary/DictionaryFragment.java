package rcd27.github.com.stasyandex.view.dictionary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.model.dictionary.dto.DefinitionDTO;
import rcd27.github.com.stasyandex.model.dictionary.dto.DicTranslationDTO;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryPresenter;
import rcd27.github.com.stasyandex.presenter.visualobject.DictionaryDefinition;
import rcd27.github.com.stasyandex.presenter.BasePresenter;
import rcd27.github.com.stasyandex.view.BaseFragment;

public class DictionaryFragment extends BaseFragment implements DictionaryView {

    private final String TAG = getClass().getSimpleName();

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
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dictionary_layout, container, false);
        ButterKnife.bind(this, view);

        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        definitionItemsRecyclerView.setLayoutManager(llm);
        dictionaryAdapter = new DictionaryAdapter(new ArrayList<>(), presenter);
        definitionItemsRecyclerView.setAdapter(dictionaryAdapter);

        return view;
    }

    @Override
    public void showDef(DefinitionDTO definitionDTO) {
        showDictionaryDefiniton(new DictionaryDefinition(definitionDTO.getText(), definitionDTO.getPos()));
        showDictionaryDictionaryItems(definitionDTO.getDicTranslationDTO());
    }

    public void showDictionaryDefiniton(DictionaryDefinition definition) {
        dictionaryOriginWord.setText(definition.getText());
        dictionaryOriginWordPos.setText(definition.getPos());
    }

    public void showDictionaryDictionaryItems(List<DicTranslationDTO> items) {
        //TODO обыграть
        assert null != items;
        dictionaryAdapter.setDictionaryItemList(items);
    }

    @Override
    public void showEmpty() {
        dictionaryOriginWord.setText("");
        dictionaryOriginWordPos.setText("");
        dictionaryAdapter.makeEmpty();
    }

    @Override
    protected BasePresenter getPresenter() {
        return presenter;
    }
}
