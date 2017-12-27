package com.github.rcd27.stasyandex.view.history;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.model.Const;
import com.github.rcd27.stasyandex.model.history.HistoryItem;

public class HistoryActivity extends AppCompatActivity {

    @BindView(R.id.ib_closeHistory)
    ImageButton btCloseHistory;

    @BindView(R.id.et_historyEditText)
    EditText editText;

    @BindView(R.id.ib_deleteHistory)
    ImageButton btDeleteHistory;

    @BindView(R.id.rw_history_recyclerView)
    RecyclerView recyclerView;

    RecyclerView.Adapter adapter;

    List<HistoryItem> historyData = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        ButterKnife.bind(this);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(llm);
        historyData = loadDataFromSharedResources();
        adapter = new HistoryAdapter(historyData, this::markAsFav);
        recyclerView.setAdapter(adapter);
    }

    private List<HistoryItem> loadDataFromSharedResources() {
        Map<String, String> items = (Map<String, String>) getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE).getAll();
        List<HistoryItem> result = new ArrayList<>();
        Gson gson = new Gson();
        for (Map.Entry<String, ?> entry : items.entrySet()) {
            String json = entry.getValue().toString();
            HistoryItem historyItem = gson.fromJson(json, HistoryItem.class);
            result.add(historyItem);
        }
        return result;
    }

    @OnClick(R.id.ib_closeHistory)
    public void onCloseButtonClick() {
        finish();
    }

    @OnClick(R.id.ib_deleteHistory)
    public void onDeleteHistoryClick() {
        SharedPreferences prefs = getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
        historyData.clear();
        adapter.notifyDataSetChanged();
    }

    protected void markAsFav(int position) {
        HistoryItem item = historyData.get(position);
        item.setMarkedFav(!item.isMarkedFav());

        String name = item.getTextFrom() + item.getTextTo() + item.getLang();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(item);

        SharedPreferences prefs = getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(name, json);
        editor.apply();

    }
}
