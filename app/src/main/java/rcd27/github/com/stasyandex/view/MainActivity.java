package rcd27.github.com.stasyandex.view;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import butterknife.ButterKnife;
import rcd27.github.com.stasyandex.R;
import rcd27.github.com.stasyandex.view.fragments.FavouritesFragment;
import rcd27.github.com.stasyandex.view.fragments.TranslateFragment;

public class MainActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if (null != findViewById(R.id.fragment_container)) {
            if (null != savedInstanceState) {
                return;
            }
        }

        TranslateFragment translateFragment = new TranslateFragment();
        translateFragment.setArguments(getIntent().getExtras());

        FavouritesFragment favouritesFragment = new FavouritesFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container,translateFragment)
                .commit();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
