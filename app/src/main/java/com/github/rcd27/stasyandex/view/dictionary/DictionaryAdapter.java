package com.github.rcd27.stasyandex.view.dictionary;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.TextUtil;
import com.github.rcd27.stasyandex.presenter.dictionary.DictionaryVisualItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private List<DictionaryVisualItem> list;

    DictionaryAdapter(List<DictionaryVisualItem> list) {
        this.list = list;
    }

    public List<DictionaryVisualItem> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_dictionary, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(DictionaryAdapter.ViewHolder holder, int position) {
        List<String> synonyms = list.get(position).getComaRaw();
        List<String> meaninigs = list.get(position).getMeaningRaw();

        if (null != synonyms) {
            holder.textSyn.setText(TextUtil.commaRawFromList(synonyms));
        }

        if (null != meaninigs) {
            holder.mean.setText("(" + TextUtil.commaRawFromList(meaninigs) + ")");
        }

        holder.itemNumber.setText(String.valueOf(position + 1));
    }

    void setDictionaryItemList(List<DictionaryVisualItem> dictionaryItemList) {
        this.list = dictionaryItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // TODO: переименовать
    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_dictionary_item_number) TextView itemNumber;
        @BindView(R.id.tv_dictionary_item_text_syn) TextView textSyn;
        @BindView(R.id.tv_dictionary_item_mean) TextView mean;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
