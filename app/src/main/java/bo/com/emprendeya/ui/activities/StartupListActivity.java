package bo.com.emprendeya.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.ui.adapters.StartupAdapter;
import bo.com.emprendeya.ui.callback.StartupCallback;
import bo.com.emprendeya.utils.Constants;
import bo.com.emprendeya.utils.ErrorMapper;
import bo.com.emprendeya.viewModel.StartupListViewModel;

public class StartupListActivity extends AppCompatActivity implements StartupCallback {

    private static final String LOG = StartupListActivity.class.getName();
    private Context context;

    private StartupListViewModel viewModel;

    private LinearLayout parentLinearLayout;
    private CarouselView carouselView;
    private RecyclerView startupRecyclerView;
    private StartupAdapter adapter;

    private List<Post> popularPosts = new ArrayList<>();
    private List<Startup> startups = new ArrayList<>();

    private ProgressDialog progressDialog;

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
        parentLinearLayout = findViewById(R.id.parentLinearLayout);
        carouselView = findViewById(R.id.carouselView);
        startupRecyclerView = findViewById(R.id.startupRecyclerView);

        adapter = new StartupAdapter(startups, context);
        startupRecyclerView.setAdapter(adapter);
        /*startupRecyclerView.setLayoutManager(
                new LinearLayoutManager(context, RecyclerView.VERTICAL, false));*/

        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Cargando, espere por favor");
        progressDialog.setCancelable(false);
    }

    private void initEvents() {
        adapter.setCallback(this);
    }

    private void getIntentValues() {
        Intent intent = getIntent();
        if (intent.hasExtra(Constants.KEY_DISPLAY_NAME)) {
            String displayName = intent.getStringExtra(Constants.KEY_DISPLAY_NAME);
            Toast.makeText(context, displayName, Toast.LENGTH_SHORT).show();
        }
    }

    private void subscribeToData() {
        viewModel.getPopularPosts().observe(this, listBase -> {
            //onChanged(Base<List<Posts>> listBase)
            //T1, Tn: Firebase
            if (listBase.isSuccess()) {
                popularPosts = listBase.getData();
                updateCarousel(popularPosts);
            } else {
                Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                        Toast.LENGTH_SHORT).show();
            }
        });

        showLoading();
        viewModel.getStartups("").observe(this, new Observer<Base<List<Startup>>>() {
            @Override
            public void onChanged(Base<List<Startup>> listBase) {
                dismissLoading();
                //T1: Local
                //T2: API
                if (listBase.isSuccess()) {
                    startups = listBase.getData();
                    adapter.updateItems(startups);
                    Log.e("getStartups", new Gson().toJson(listBase));
                } else {
                    Toast.makeText(context, ErrorMapper.getError(context, listBase.getErrorCode()),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void updateCarousel(List<Post> posts) {
        carouselView.setImageListener(imageListener);
        carouselView.setPageCount(Constants.CAROUSEL_COUNT);
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            if (position < popularPosts.size()) {
                Picasso.get().load(popularPosts.get(position).getCoverPhoto()).into(imageView);
            }
        }
    };

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

    @Override
    public void onStartupClicked(Startup startup) {
        Intent intent = new Intent(context, StartupDetailsActivity.class);
        intent.putExtra(Constants.KEY_STARTUP_SELECTED, new Gson().toJson(startup));
        startActivity(intent);
    }

    private void showLoading() {
        progressDialog.show();
    }

    private void dismissLoading() {
        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
    }

    public void logout(View view) {
        viewModel.logout();
        finish();
    }
}