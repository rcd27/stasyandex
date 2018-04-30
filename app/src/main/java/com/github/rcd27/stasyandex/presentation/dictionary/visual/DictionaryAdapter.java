package com.github.rcd27.stasyandex.presentation.dictionary.visual;


import android.annotation.*;
import android.support.annotation.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.common.*;

import java.util.*;

import butterknife.*;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.DictionaryAdapterViewHolder> {

  private List<DictionaryVisualItem> list;

  public DictionaryAdapter(List<DictionaryVisualItem> list) {
    this.list = list;
  }

  public List<DictionaryVisualItem> getList() {
    return list;
  }

  @NonNull
  @Override
  public DictionaryAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View v = LayoutInflater
        .from(parent.getContext())
        .inflate(R.layout.item_dictionary, parent, false);

    return new DictionaryAdapterViewHolder(v);
  }

  @SuppressLint("SetTextI18n")
  @Override
  public void onBindViewHolder(@NonNull DictionaryAdapter.DictionaryAdapterViewHolder holder, int position) {
    List<String> synonyms = list.get(position).getComaRaw();
    List<String> meanings = list.get(position).getMeaningRaw();

    if (null != synonyms) {
      holder.textSyn.setText(TextUtil.commaRawFromList(synonyms));
    }

    if (null != meanings) {
      holder.mean.setText("(" + TextUtil.commaRawFromList(meanings) + ")");
    }

    holder.itemNumber.setText(String.valueOf(position + 1));
  }

  public void setDictionaryItemList(List<DictionaryVisualItem> dictionaryItemList) {
    this.list = dictionaryItemList;
    notifyDataSetChanged();
  }

  @Override
  public int getItemCount() {
    return list.size();
  }

  static class DictionaryAdapterViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_dictionary_item_number) TextView itemNumber;
    @BindView(R.id.tv_dictionary_item_text_syn) TextView textSyn;
    @BindView(R.id.tv_dictionary_item_mean) TextView mean;

    DictionaryAdapterViewHolder(View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
    }
  }
}
