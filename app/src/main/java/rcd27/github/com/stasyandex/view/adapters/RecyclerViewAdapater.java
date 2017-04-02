package rcd27.github.com.stasyandex.view.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.model.data.AvailableLanguages;
import rcd27.github.com.stasyandex.model.data.Langs;

public class RecyclerViewAdapater extends RecyclerView.Adapter<RecyclerViewAdapater.ViewHolder> {

    private AvailableLanguages availableLanguages = new AvailableLanguages();
    private List<String> directions = new ArrayList<>();
    private Langs languages;

    public void setAvailableLanguages(AvailableLanguages setFrom) {
        this.availableLanguages = setFrom;
        this.directions = availableLanguages.getDirections();
        this.languages = availableLanguages.getLanguages();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.available_languages_directions, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String direction = directions.get(position);
        holder.direction.setText(direction);
    }

    @Override
    public int getItemCount() {
        return directions.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView direction;

        public ViewHolder(View itemView) {
            super(itemView);
            direction = (TextView) itemView.findViewById(R.id.directionTextView);
        }
    }
}
