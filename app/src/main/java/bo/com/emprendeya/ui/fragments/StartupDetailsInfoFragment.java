package bo.com.emprendeya.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import bo.com.emprendeya.R;
import bo.com.emprendeya.viewModel.StartupDetailsViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartupDetailsInfoFragment extends Fragment {

    private static final String LOG = StartupDetailsInfoFragment.class.getSimpleName();
    private Context context;


    private StartupDetailsViewModel startupDetailsViewModel;

    public static StartupDetailsInfoFragment newInstance() {
        StartupDetailsInfoFragment fragment = new StartupDetailsInfoFragment();
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
        startupDetailsViewModel = ViewModelProviders.of(this).get(StartupDetailsViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_startup_details_info, container, false);
        initViews(root);
        initEvents();
        return root;
    }

    private void initViews(View view) {
        //Buscar los IDs dentro view.
    }

    private void initEvents() {

    }
}