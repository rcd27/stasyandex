package rcd27.github.com.stasyandex.view.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import rcd27.github.com.stasyandex.R;

public abstract class BaseAdapter<T> extends RecyclerView.Adapter<BaseAdapter.ViewHolder> {

    private final String TAG = getClass().getSimpleName();

    protected List<String> list;

    public BaseAdapter(List<String> list) {
        this.list = list;
    }

    public List<String> getList() {
        return list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.translation_item_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView originText;
        TextView translationText;

        public ViewHolder(View itemView) {
            super(itemView);
            originText = (TextView) itemView.findViewById(R.id.trlt_origin_text);
            translationText = (TextView) itemView.findViewById(R.id.trlt_translation);
        }
    }
}
