package bo.com.emprendeya.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.viewModel.StartupListViewModel;

public class StartupListActivity extends AppCompatActivity {

    private static final String LOG = StartupListActivity.class.getName();
    private Context context;

    private StartupListViewModel viewModel;

    private List<Post> popularPosts = new ArrayList<>();
    private List<Startup> startups = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");

        setContentView(R.layout.activity_startup_list);
        context = this;

        //Injectando el viewModel
        viewModel = new ViewModelProvider(this).get(StartupListViewModel.class);

        initViews();
        initEvents();
        getIntentValues();
        subscribeToData();
    }

    private void initViews() {

    }

    private void initEvents() {

    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayName = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayName, Toast.LENGTH_SHORT).show();
        }
    }

    private void subscribeToData() {
        viewModel.getStartups("").observe(this, new Observer<Base<List<Startup>>>() {
            @Override
            public void onChanged(Base<List<Startup>> listBase) {
                //T1: Local
                //T2: API

                Log.e("getStartups", new Gson().toJson(listBase));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.w(LOG, "onStart");
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.w(LOG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.w(LOG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.w(LOG, "onStop");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.w(LOG, "onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.w(LOG, "onDestroy");
    }
}