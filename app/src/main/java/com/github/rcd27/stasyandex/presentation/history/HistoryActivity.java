package com.github.rcd27.stasyandex.presentation.history;


import android.content.*;
import android.os.*;
import android.support.annotation.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.widget.*;

import com.github.rcd27.stasyandex.R;
import com.github.rcd27.stasyandex.common.*;
import com.github.rcd27.stasyandex.model.data.history.*;
import com.google.gson.*;

import java.util.*;

import butterknife.*;

public class HistoryActivity extends AppCompatActivity {

  @BindView(R.id.ib_closeHistory) ImageButton btCloseHistory;
  @BindView(R.id.et_historyEditText) EditText editText;
  @BindView(R.id.ib_deleteHistory) ImageButton btDeleteHistory;
  @BindView(R.id.rw_history_recyclerView) RecyclerView recyclerView;

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
    Map<String, ?> items = getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE).getAll();
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
    item.setMarkedFav(!item.isMarkedFav);

    String name = item.from + item.to + item.lang;
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(item);

    SharedPreferences prefs = getSharedPreferences(Const.HISTORY_CACHE, Context.MODE_PRIVATE);
    SharedPreferences.Editor editor = prefs.edit();

    editor.putString(name, json);
    editor.apply();
  }
}
