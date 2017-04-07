package rcd27.github.com.stasyandex.view.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;


public class FavouritesFragment extends Fragment{
    @Bind(R.id.favourites_search_edit_text)
    EditText searchEditText;

    @Bind(R.id.favourites_listview)
    ListView listView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO тут подгружать данные из БД
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourites_layout, container, false);
        ButterKnife.bind(this, view);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.fake_list_data, R.layout.favourites_layout);
        listView.setAdapter(adapter);

        return view;
    }
}
