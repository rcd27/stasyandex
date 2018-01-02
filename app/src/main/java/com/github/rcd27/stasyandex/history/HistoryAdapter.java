package com.github.rcd27.stasyandex.history;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.github.rcd27.stasyandex.OnFavClickListener;
import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.data.history.HistoryItem;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryAdapterViewHolder> {

    private final List<HistoryItem> historyItems;
    private final OnFavClickListener onFavClickListener;

    HistoryAdapter(@NonNull List<HistoryItem> historyItems,
                   @NonNull OnFavClickListener onFavClickListener) {

        this.historyItems = historyItems;
        this.onFavClickListener = onFavClickListener;
    }

    @Override
    public HistoryAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history, parent, false);
        return new HistoryAdapterViewHolder(view, onFavClickListener);
    }

    @Override
    public void onBindViewHolder(HistoryAdapterViewHolder holder, int position) {
        holder.from.setText(historyItems.get(position).getTextFrom());
        holder.to.setText(historyItems.get(position).getTextTo());
        holder.lang.setText(historyItems.get(position).getLang());
        holder.fav.setSelected(historyItems.get(position).isMarkedFav());
    }

    @Override
    public int getItemCount() {
        return historyItems.size();
    }

    public static class HistoryAdapterViewHolder extends RecyclerView.ViewHolder {

        public final TextView from;
        public final TextView to;
        public final TextView lang;

        final ImageButton fav;

        HistoryAdapterViewHolder(View view, OnFavClickListener listener) {
            super(view);
            from = view.findViewById(R.id.tv_history_from);
            to = view.findViewById(R.id.tv_history_to);
            lang = view.findViewById(R.id.tv_history_lang);
            fav = view.findViewById(R.id.ib_history_fav);

            setFavListener(listener);
        }

        private void setFavListener(final OnFavClickListener listener) {
            fav.setOnClickListener(v -> {
                if (listener != null) {
                    fav.setSelected(!fav.isSelected());
                    listener.onFavClick(getAdapterPosition());
                }
            });
        }
    }
}
