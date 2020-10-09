package bo.com.emprendeya.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import bo.com.emprendeya.R;
import bo.com.emprendeya.viewModel.StartupDetailsViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartupPostsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";

    private StartupDetailsViewModel viewModel;

    public static StartupPostsFragment newInstance(int index) {
        StartupPostsFragment fragment = new StartupPostsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(StartupDetailsViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_posts, container, false);
        return root;
    }
}