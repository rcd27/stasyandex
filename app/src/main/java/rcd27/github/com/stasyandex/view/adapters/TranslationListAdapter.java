package rcd27.github.com.stasyandex.view.adapters;


import java.util.List;

import rcd27.github.com.stasyandex.model.data.Translation;
import rcd27.github.com.stasyandex.presenter.TranslatePresenter;

public class TranslationListAdapter extends BaseAdapter<Translation> {

    private TranslatePresenter presenter;

    public TranslationListAdapter(List<Translation> list, TranslatePresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

        public void setTranslationList(List<Translation> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        Translation translation = list.get(position);
        holder.originText.setText(presenter.getOriginWord());
        holder.translationText.setText(translation.getTranslationResult().toString());
    }
}
