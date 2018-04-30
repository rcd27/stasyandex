package com.github.rcd27.stasyandex.presentation.history;


import android.support.annotation.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;

import com.github.rcd27.stasyandex.*;
import com.github.rcd27.stasyandex.model.data.history.*;

import java.util.*;

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
    holder.from.setText(historyItems.get(position).from);
    holder.to.setText(historyItems.get(position).to);
    holder.lang.setText(historyItems.get(position).lang);
    holder.fav.setSelected(historyItems.get(position).isMarkedFav);
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
