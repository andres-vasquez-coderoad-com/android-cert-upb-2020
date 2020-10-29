package bo.com.emprendeya.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.gson.Gson;

import java.util.List;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Base;
import bo.com.emprendeya.model.Post;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.viewModel.StartupDetailsViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartupDetailsPostsFragment extends Fragment {

    private static final String LOG = StartupDetailsPostsFragment.class.getSimpleName();
    private Context context;


    private StartupDetailsViewModel startupDetailsViewModel;

    public static StartupDetailsPostsFragment newInstance() {
        StartupDetailsPostsFragment fragment = new StartupDetailsPostsFragment();
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startupDetailsViewModel = ViewModelProviders.of(requireActivity()).get(StartupDetailsViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_startup_details_posts, container, false);
        initViews(root);
        initEvents();
        subscribeToData();
        return root;
    }

    private void initViews(View view) {
        //Buscar los IDs dentro view.
    }

    private void initEvents() {

    }

    private void subscribeToData() {
        startupDetailsViewModel.getStartup().observe(requireActivity(), startup -> {
            observePost(startup.getUuid());
        });
    }

    private void observePost(String uuid) {
        startupDetailsViewModel.observePosts(uuid).observe(requireActivity(), listBase -> {
            if (listBase.isSuccess()) {
                List<Post> posts = listBase.getData();
                Log.e("Posts", new Gson().toJson(posts));
            } else {

            }
        });
    }
}