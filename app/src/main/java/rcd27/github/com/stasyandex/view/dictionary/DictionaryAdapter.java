package rcd27.github.com.stasyandex.view.dictionary;


import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.TextUtil;
import rcd27.github.com.stasyandex.model.dictionary.dto.DicTranslation;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private List<DicTranslation> list;

    DictionaryAdapter(List<DicTranslation> list) {
        this.list = list;
    }

    public List<DicTranslation> getList() {
        return list;
    }

    void makeEmpty() {
        list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_dictionary, parent, false);
        return new ViewHolder(v);
    }

    /*Присваивание текст-вьюшкам значений из листа*/
    @Override
    public void onBindViewHolder(DictionaryAdapter.ViewHolder holder, int position) {
        //TODO привести в порядок
//        List<String> synonyms = list.get(position).getSynonyms();
//        List<String> meaninigs = list.get(position).getMeanings();

//        if (null != synonyms) {
//            holder.textSyn.setText(TextUtil.commaRawFromList(synonyms));
//        }
//
//        if (null != meaninigs) {
//            holder.mean.setText(TextUtil.commaRawFromList(meaninigs));
//        }
    }

    public void setDictionaryItemList(List<DicTranslation> dictionaryItemList) {
        this.list = dictionaryItemList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.tv_dictionary_item_number)
        TextView itemNumber;
        @Bind(R.id.tv_dictionary_item_text_syn)
        TextView textSyn;
        @Bind(R.id.tv_dictionary_item_mean)
        TextView mean;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
