package bo.com.emprendeyalo.preparation.ui.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.util.Map;

import bo.com.emprendeyalo.preparation.R;
import bo.com.emprendeyalo.preparation.models.Startup;
import bo.com.emprendeyalo.preparation.viewModel.StartupDetailsViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartupDetailsFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    private StartupDetailsViewModel viewModel;
    private Context context;

    private ImageView coverImageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private LinearLayout covidDetailsLinearLayout;

    public static StartupDetailsFragment newInstance(int index) {
        StartupDetailsFragment fragment = new StartupDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = ViewModelProviders.of(requireActivity()).get(StartupDetailsViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_startup_details, container, false);
        initViews(root);
        initEvents();
        subscribeToEvents();
        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initViews(View view) {
        coverImageView = view.findViewById(R.id.coverImageView);
        nameTextView = view.findViewById(R.id.nameTextView);
        addressTextView = view.findViewById(R.id.addressTextView);
        covidDetailsLinearLayout = view.findViewById(R.id.covidDetailsLinearLayout);
    }

    private void initEvents() {

    }

    private void subscribeToEvents() {
        viewModel.getStartup().observe(requireActivity(), new Observer<Startup>() {
            @Override
            public void onChanged(Startup startup) {
                nameTextView.setText(startup.getDisplayName());
                addressTextView.setText(startup.getAddress());
                Picasso.get().load(startup.getCoverPhoto()).into(coverImageView);
                addCovidDetails(startup.getCodivInfo());
            }
        });
    }

    private void addCovidDetails(Map<String, String> covidInfo) {
        covidDetailsLinearLayout.removeAllViews();
        if (covidInfo != null) {
            for (Map.Entry<String, String> entry : covidInfo.entrySet()) {
                TextView tx = new TextView(context);
                tx.setText(entry.getKey() + ": " + entry.getValue());
                covidDetailsLinearLayout.addView(tx);
            }
        }
    }
}