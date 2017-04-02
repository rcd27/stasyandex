package rcd27.github.com.stasyandex.view;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.model.data.AvailableLanguages;
import rcd27.github.com.stasyandex.presenter.AvailableLanguagesPresenter;
import rcd27.github.com.stasyandex.presenter.Presenter;
import rcd27.github.com.stasyandex.view.adapters.RecyclerViewAdapater;

public class MainActivity extends AppCompatActivity implements View {

    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Bind(R.id.toolbar)
    Toolbar toolbar;

    @Bind(R.id.editText)
    EditText editText;

    @Bind(R.id.button)
    Button getDirsButton;

    private RecyclerViewAdapater adapter;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);

        presenter = new AvailableLanguagesPresenter(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        adapter = new RecyclerViewAdapater();
        recyclerView.setAdapter(adapter);

        getDirsButton.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                presenter.onGetDirsButtonClick();
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (null != presenter) {
            presenter.onStop();
        }
    }

    @Override
    public void showAvailableLanguages(AvailableLanguages availableLanguages) {
        adapter.setAvailableLanguages(availableLanguages);
    }

    @Override
    public void showError(String error) {
        makeToast(error);
    }


    @Override
    public void showEmptyAvaialableLanguages() {
        makeToast("Список доступных языков пуст");
    }

    @Override
    public String forLanguage() {
        return editText.getText().toString();
    }

    private void makeToast(String error) {
        Snackbar.make(toolbar,error,Snackbar.LENGTH_LONG).show();
    }
}
