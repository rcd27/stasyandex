package rcd27.github.com.stasyandex.view.dictionary;


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
import rcd27.github.com.stasyandex.TextUtil;
import rcd27.github.com.stasyandex.model.dictionary.dto.DicTranslationDTO;
import rcd27.github.com.stasyandex.model.dictionary.dto.MeaninigDTO;
import rcd27.github.com.stasyandex.model.dictionary.dto.SynonymDTO;
import rcd27.github.com.stasyandex.presenter.dictionary.DictionaryPresenter;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private List<DicTranslationDTO> list;

    private DictionaryPresenter presenter;

    public DictionaryAdapter(List<DicTranslationDTO> list, DictionaryPresenter presenter) {
        this.list = list;
        this.presenter = presenter;
    }

    public List<DicTranslationDTO> getList() {
        return list;
    }

    public void makeEmpty() {
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
        List<SynonymDTO> synonymDTOs = list.get(position).getSynonymDTO();
        List<MeaninigDTO> meaninigDTOs = list.get(position).getMeaninigDTO();

        if (null != synonymDTOs) {
            holder.textSyn.setText(TextUtil.commaRawFromSynList(synonymDTOs));
        }

        if (null != meaninigDTOs) {
            holder.mean.setText(TextUtil.commaRawFromMeanList(meaninigDTOs));
        }
    }

    public void setDictionaryItemList(List<DicTranslationDTO> dictionaryItemList) {
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
