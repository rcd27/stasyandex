package rcd27.github.com.stasyandex.view.adapters;


import java.util.List;

import rcd27.github.com.stasyandex.model.dto.translate.TranslationDTO;
import rcd27.github.com.stasyandex.presenter.TranslatePresenter;

@Deprecated
public class TranslationListAdapter extends BaseAdapter<TranslationDTO> {

    private TranslatePresenter presenter;

    public TranslationListAdapter(List<String> list, TranslatePresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

        public void setTranslationList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        String translation = list.get(position);
        holder.translationText.setText(translation);
    }
}
