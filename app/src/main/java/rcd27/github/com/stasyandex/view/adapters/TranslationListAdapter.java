package rcd27.github.com.stasyandex.view.adapters;


import java.util.List;

import rcd27.github.com.stasyandex.model.dto.TranslationDTO;
import rcd27.github.com.stasyandex.presenter.TranslatePresenter;

public class TranslationListAdapter extends BaseAdapter<TranslationDTO> {

    private TranslatePresenter presenter;

    public TranslationListAdapter(List<TranslationDTO> list, TranslatePresenter presenter) {
        super(list);
        this.presenter = presenter;
    }

        public void setTranslationList(List<TranslationDTO> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(BaseAdapter.ViewHolder holder, int position) {
        TranslationDTO translationDTO = list.get(position);
        holder.originText.setText(presenter.getOriginWord());
        holder.translationText.setText(translationDTO.getTranslationResult().toString());
    }
}
