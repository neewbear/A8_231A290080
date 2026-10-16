package vn.edu.vhu.ltdd.a8fragment;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "A8_231A290080";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNav =
                findViewById(R.id.bottomNav);

        bottomNav.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.tab_home) {
                doiTab(new HomeFragment());
            } else if (id == R.id.tab_news) {
                doiTab(new NewsListFragment());
            } else if (id == R.id.tab_profile) {
                doiTab(ProfileFragment.newInstance(
                        "Tô Thanh Lâm", "231A290080"));
            }

            return true;
        });

        if (savedInstanceState == null) {
            bottomNav.setSelectedItemId(R.id.tab_home);
        }
    }

    private void doiTab(Fragment fragment) {
        Log.d(TAG, "Chuyển tab: "
                + fragment.getClass().getSimpleName());

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainer, fragment)
                .setReorderingAllowed(true)
                .commit();
    }
}
