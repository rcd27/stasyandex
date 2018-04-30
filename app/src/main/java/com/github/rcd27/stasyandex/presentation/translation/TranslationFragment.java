package com.github.rcd27.stasyandex.presentation.translation;


import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.view.*;
import android.widget.*;

import com.github.rcd27.airbag.*;
import com.github.rcd27.stasyandex.*;
import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.di.translation.*;
import com.github.rcd27.stasyandex.model.data.translation.*;
import com.github.rcd27.stasyandex.presentation.*;
import com.github.rcd27.stasyandex.presentation.history.*;

import javax.inject.*;

import butterknife.*;
import timber.log.*;

public class TranslationFragment extends BaseFragment implements TranslationContract.View {

  private final String tag = getClass().getSimpleName();

  @BindView(R.id.tv_languageFrom) TextView tvLanguageFrom;
  @BindView(R.id.ib_switch_direction) ImageButton ibSwitchDirection;
  @BindView(R.id.tv_languageTo) TextView tvLanguageTo;
  @BindView(R.id.translation_editText) EditText editText;
  @BindView(R.id.bt_clearEditText) ImageButton btClearEditText;
  @BindView(R.id.tv_translation_result) TextView tvTranslationResult;
  @BindView(R.id.bt_history) ImageButton historyButton;

  @Inject TranslationContract.Presenter presenter;

  // FIXME: убрать
  private TranslateButtonListener listener;

  @Override
  public void onAttach(Context context) {
    super.onAttach(context);
    try {
      listener = (TranslateButtonListener) context;
      Timber.tag(tag).i("onAttach: listener attached!");
    } catch (ClassCastException e) {
      throw new ClassCastException(context.toString() +
          " must implement TranslateButtonListener");
    }
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Stasyandex.getInstance().getAppComponent()
        .plus(new TranslationModule(this, getContext()))
        .inject(this);
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
                           @Nullable ViewGroup container,
                           Bundle savedInstanceState) {

    View view = inflater.inflate(R.layout.fragment_translation, container, false);
    ButterKnife.bind(this, view);

    /*ДЛЯ ДЕФОЛТА*/
    showLanguageFrom("русский");
    showLanguageTo("сербский");

    return view;
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    presenter.handleIntentForSelectedLanguages(data);
  }

  @OnClick(R.id.tv_languageFrom)
  public void onClickLanguageFrom() {
    presenter.onChooseLanguage(1);
  }

  @OnClick(R.id.tv_languageTo)
  public void onClickLanguageTo() {
    presenter.onChooseLanguage(2);
  }

  @OnClick(R.id.bt_clearEditText)
  public void onClearEditTextButtonClicked() {
    presenter.clearTranslationEditText();
  }

  @OnClick(R.id.bt_history)
  public void onHistoryButtonClicked() {
    presenter.switchToHistory();
  }

  // FIXME: replace with RxBindings
  @OnTextChanged(R.id.translation_editText)
  public void onTextChanged() {
    presenter.getTranslationForTextFromEditText();
    listener.onTranslateEditTextChanged(presenter.getDirection(), getTextFromEditText());
  }

  @Override
  public void showError(String error) {
    makeToast(tvTranslationResult, error);
  }


  @Override
  public String getTextFromEditText() {
    return editText.getText().toString();
  }

  @Override
  public void showTranslation(Translation resultTranslation) {
    tvTranslationResult.setText(resultTranslation.show());
  }

  @Override
  public void showEmpty() {
    tvTranslationResult.setText("");
  }

  @Override
  protected BasePresenter getPresenter() {
    return (BasePresenter) presenter;
  }

  @Override
  public void chooseLanguage(final int direction) {
    Intent intent = new Intent(getContext(), LanguagesActivity.class);
    intent.putExtra("direction", direction);
    startActivityForResult(intent, 0);
  }

  @Override
  public void openHistory() {
    Intent intent = new Intent(getContext(), HistoryActivity.class);
    startActivity(intent);
  }

  @Override
  public void clearEditText() {
    editText.setText("");
  }

  @Override
  public void showState(ApiRequest.RequestState state) {
    // TODO: implement
  }

  @Override
  public void showLanguageFrom(String selectedLanguage) {
    tvLanguageFrom.setText(selectedLanguage);
  }

  @Override
  public void showLanguageTo(String selectedLanguage) {
    tvLanguageTo.setText(selectedLanguage);
  }

  @Override
  public String getLanguageFrom() {
    return tvLanguageFrom.getText().toString();
  }

  @Override
  public String getLanguageTo() {
    return tvLanguageTo.getText().toString();
  }

  public interface TranslateButtonListener {
    void onTranslateEditTextChanged(String direction, String textFromEditText);
  }
}
