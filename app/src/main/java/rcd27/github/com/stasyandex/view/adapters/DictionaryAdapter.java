package rcd27.github.com.stasyandex.view.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.presenter.DictionaryPresenter;
import rcd27.github.com.stasyandex.presenter.visualobjects.DictionaryItem;

public class DictionaryAdapter extends RecyclerView.Adapter<DictionaryAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    private List<DictionaryItem> list;

    private DictionaryPresenter presenter;

    public DictionaryAdapter(List<DictionaryItem> list, DictionaryPresenter presenter) {
        this.list = list;
        this.presenter = presenter;
    }

    public List<DictionaryItem> getList() {
        return list;
    }

    @Override
    public DictionaryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.dictionary_item_layout, parent, false);
        return new ViewHolder(v);
    }

    /*Присваивание текст-вьюшкам значений из листа*/
    @Override
    public void onBindViewHolder(DictionaryAdapter.ViewHolder holder, int position) {
        holder.itemNumber.setText(position);
        holder.textSyn.setText(list.get(position).getTextSyn());
        holder.mean.setText(list.get(position).getMean());
    }

    public void setDictionaryItemList(List<DictionaryItem> dictionaryItemList) {
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
            ButterKnife.bind(itemView);
        }
    }
}
