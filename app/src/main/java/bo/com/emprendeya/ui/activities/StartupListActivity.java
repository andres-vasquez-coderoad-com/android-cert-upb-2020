package bo.com.emprendeya.ui.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import bo.com.emprendeya.R;
import bo.com.emprendeya.models.Base;
import bo.com.emprendeya.models.Post;
import bo.com.emprendeya.models.Startup;
import bo.com.emprendeya.ui.adapters.StartupAdapter;
import bo.com.emprendeya.viewModel.LoginViewModel;
import bo.com.emprendeya.viewModel.StartupListViewModel;

public class StartupListActivity extends AppCompatActivity {

    private static final String LOG = StartupListActivity.class.getName();
    private StartupListViewModel viewModel;

    private Context context;

    private List<Post> posts = new ArrayList<>();
    private List<Startup> startups = new ArrayList<>();

    private NavigationView navigationView;
    private CarouselView carouselView;
    private RecyclerView startupRecyclerView;

    private String category = null;

    private StartupAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.w(LOG, "onCreate");
        viewModel = new ViewModelProvider(this).get(StartupListViewModel.class);
        this.context = this;
        getSupportActionBar().hide();

        setContentView(R.layout.activity_startup_list);
        initViews();
        initEvents();
        subscribe();
    }

    private void initViews() {
        carouselView = findViewById(R.id.carouselView);
        startupRecyclerView = findViewById(R.id.startupRecyclerView);
        navigationView = findViewById(R.id.navigationView);

        adapter = new StartupAdapter(startups, context);
        startupRecyclerView.setAdapter(adapter);
        startupRecyclerView.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
    }

    private void initEvents() {

    }

    private void subscribe() {
        this.viewModel.getMostLikelyPosts().observe(this, listBase -> {
            if (listBase.isSuccess()) {
                posts = listBase.getData();
                updateCarrousel(posts);
            }
        });

        this.viewModel.getStartups(category).observe(this, listBase -> {
            if (listBase.isSuccess()) {
                startups = listBase.getData();
                adapter.updateItems(startups);
            }
        });
    }

    private void updateCarrousel(final List<Post> postList) {
        if (postList.size() > 0) {
            //Carrousel
            carouselView.setVisibility(View.VISIBLE);
            carouselView.setImageListener(imageListener);
            carouselView.setPageCount(postList.size());
        }
    }

    ImageListener imageListener = new ImageListener() {
        @Override
        public void setImageForPosition(int position, ImageView imageView) {
            if (position < posts.size()) {
                Picasso.get().load(posts.get(position).getCoverPhoto()).into(imageView);
            }
        }
    };
}