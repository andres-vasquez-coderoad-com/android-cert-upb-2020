package bo.com.emprendeya.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.ui.adapters.StartupDetailsPagerAdapter;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.viewModel.LoginViewModel;
import bo.com.emprendeya.viewModel.StartupDetailsViewModel;

public class StartupDetailsActivity extends AppCompatActivity {

    private static final String LOG = StartupDetailsActivity.class.getSimpleName();
    private Context context;

    private ViewPager viewPager;
    private TabLayout tabs;
    private FloatingActionButton fab;

    private StartupDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_details);
        context = this;
        viewModel = new ViewModelProvider(this).get(StartupDetailsViewModel.class);

        initViews();
        initEvents();
        getIntentValues();
    }

    private void initViews() {
        StartupDetailsPagerAdapter startupDetailsPagerAdapter = new StartupDetailsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(startupDetailsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        fab = findViewById(R.id.fab);
    }

    private void initEvents() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_STARTUP_SELECTED)) {
            try {
                String json = intent.getStringExtra(Constants.KEY_STARTUP_SELECTED);
                Startup startup = new Gson().fromJson(json, Startup.class);
                viewModel.setStartup(startup);
                Log.e("StartupName", startup.getDisplayName());
            } catch (Exception ex) {
                Log.e(LOG, "getIntentValues", ex);
            }
        }
    }
}