package com.github.rcd27.stasyandex.view.history;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.model.history.HistoryItem;
import com.github.rcd27.stasyandex.view.OnFavClickListener;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    private List<HistoryItem> dataset;
    OnFavClickListener onFavClickListener;

    public HistoryAdapter(List<HistoryItem> dataset, OnFavClickListener onFavClickListener) {
        this.dataset = dataset;
        this.onFavClickListener = onFavClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new ViewHolder(view, onFavClickListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.from.setText(dataset.get(position).getTextFrom());
        holder.to.setText(dataset.get(position).getTextTo());
        holder.lang.setText(dataset.get(position).getLang());
        holder.fav.setSelected(dataset.get(position).isMarkedFav());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView from;
        public TextView to;
        public TextView lang;
        public ImageButton fav;

        public ViewHolder(View view, OnFavClickListener listener) {
            super(view);
            from = view.findViewById(R.id.tv_history_from);
            to = view.findViewById(R.id.tv_history_to);
            lang = view.findViewById(R.id.tv_history_lang);
            fav = view.findViewById(R.id.ib_history_fav);

            setFavListener(listener);
        }

        private void setFavListener(final OnFavClickListener listener) {
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        fav.setSelected(!fav.isSelected());
                        listener.onFavClick(getAdapterPosition());
                    }
                }
            });
        }
    }
}
