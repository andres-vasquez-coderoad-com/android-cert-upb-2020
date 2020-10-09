package bo.com.emprendeya.ui.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;

import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import bo.com.emprendeya.R;
import bo.com.emprendeya.models.Startup;
import bo.com.emprendeya.ui.adapters.StartupDetailsPagerAdapter;
import bo.com.emprendeya.viewModel.StartupDetailsViewModel;

public class StartupDetailsActivity extends AppCompatActivity {

    private StartupDetailsViewModel viewModel;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup_details);
        viewModel = new ViewModelProvider(this).get(StartupDetailsViewModel.class);
        context = this;
        initViews();
    }

    private void initViews() {
        StartupDetailsPagerAdapter sectionsPagerAdapter = new StartupDetailsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fillInfo();
    }

    private void fillInfo() {
        Intent intent = getIntent();
        if (intent.hasExtra("startup")) {
            Startup startup = new Gson().fromJson(intent.getStringExtra("startup"), Startup.class);
            viewModel.setStartup(startup);
        }
    }
}