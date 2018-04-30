package com.github.rcd27.stasyandex.presentation.dictionary;


import android.os.*;
import android.support.annotation.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.*;
import com.github.rcd27.stasyandex.di.dictionary.*;
import com.github.rcd27.stasyandex.presentation.*;
import com.github.rcd27.stasyandex.presentation.dictionary.visual.*;

import java.util.*;

import javax.inject.*;

import butterknife.*;

public class DictionaryFragment extends BaseFragment implements DictionaryContract.View {

  private final String TAG = getClass().getSimpleName();

  @BindView(R.id.tv_dictionary_word) TextView dictionaryOriginWord;
  @BindView(R.id.tv_dictionary_pos) TextView dictionaryOriginWordPos;
  @BindView(R.id.dictionary_recycler_view) RecyclerView definitionItemsRecyclerView;

  @Inject DictionaryContract.Presenter presenter;

  private DictionaryAdapter dictionaryAdapter;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Stasyandex.getInstance().getAppComponent()
        .plus(new DictionaryModule(this))
        .inject(this);
  }

  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           @Nullable Bundle savedInstanceState) {

    return inflater.inflate(R.layout.fragment_dictionary, container, false);
  }

  @Override
  public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    ButterKnife.bind(this, view);

    LinearLayoutManager llm = new LinearLayoutManager(getContext());
    definitionItemsRecyclerView.setLayoutManager(llm);
    dictionaryAdapter = new DictionaryAdapter(new ArrayList<>());
    definitionItemsRecyclerView.setAdapter(dictionaryAdapter);
  }

  @Override
  public void showError(String text) {
    makeToast(definitionItemsRecyclerView, text);
  }

  @Override
  public void showDefinition(DictionaryVisualDefinition definition) {
    dictionaryOriginWord.setText(definition.text);
    dictionaryOriginWordPos.setText(definition.pos);
  }

  @Override
  public void showDictionaryItems(List<DictionaryVisualItem> items) {
    dictionaryAdapter.setDictionaryItemList(items);
  }

  @Override
  public void showEmpty() {
    dictionaryOriginWord.setText("");
    dictionaryOriginWordPos.setText("");
    dictionaryAdapter.setDictionaryItemList(Collections.emptyList());
  }

  @Override
  public DictionaryPresenter getPresenter() {
    return (DictionaryPresenter) presenter;
  }
}
