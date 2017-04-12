package rcd27.github.com.stasyandex.fragments.favourites.view;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;


public class FavouritesFragment extends Fragment {
    @Bind(R.id.favourites_search_edit_text)
    EditText searchEditText;

    @Bind(R.id.favourites_recycler_view)
    RecyclerView recyclerView;

    public static FavouritesFragment newInstance() {
        //TODO в туториале тут передаётся репозиторий.
        return new FavouritesFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO тут подгружать данные из БД
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favourites_layout, container, false);
        ButterKnife.bind(this, view);

        return view;
    }
}
