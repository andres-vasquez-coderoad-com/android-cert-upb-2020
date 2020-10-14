package bo.com.emprendeya.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.squareup.picasso.Picasso;

import java.util.Map;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.viewModel.StartupDetailsViewModel;

/**
 * A placeholder fragment containing a simple view.
 */
public class StartupDetailsInfoFragment extends Fragment {

    private static final String LOG = StartupDetailsInfoFragment.class.getSimpleName();
    private Context context;

    private StartupDetailsViewModel startupDetailsViewModel;

    private ImageView coverImageView;
    private TextView nameTextView;
    private TextView addressTextView;
    private LinearLayout covidDetailsLinearLayout;

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
        startupDetailsViewModel = ViewModelProviders.of(requireActivity()).get(StartupDetailsViewModel.class);
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_startup_details_info, container, false);
        initViews(root);
        initEvents();
        subscribeToData();
        return root;
    }

    private void initViews(View view) {
        //Buscar los IDs dentro view.
        coverImageView = view.findViewById(R.id.coverImageView);
        nameTextView = view.findViewById(R.id.nameTextView);
        addressTextView = view.findViewById(R.id.addressTextView);
        covidDetailsLinearLayout = view.findViewById(R.id.covidDetailsLinearLayout);
    }

    private void initEvents() {

    }

    private void subscribeToData() {
        startupDetailsViewModel.getStartup().observe(requireActivity(), new Observer<Startup>() {
            @Override
            public void onChanged(Startup startup) {
                nameTextView.setText(startup.getDisplayName());
                addressTextView.setText(startup.getAddress());
                Picasso.get().load(startup.getCoverPhoto()).into(coverImageView);

                covidDetailsLinearLayout.removeAllViews();
                for (int i = 0; i < 10; i++) {
                    addCovidInfo(startup.getCodivInfo());
                }
            }
        });
    }

    private void addCovidInfo(Map<String, String> covidInfo) {
        if (covidInfo != null) {
            for (Map.Entry<String, String> entry : covidInfo.entrySet()) {
                TextView tvKey = new TextView(context);
                tvKey.setLayoutParams(new LinearLayout.LayoutParams(0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        40));
                tvKey.setTextSize(22);
                tvKey.setText(entry.getKey());

                TextView tvValue = new TextView(context);
                tvValue.setLayoutParams(new LinearLayout.LayoutParams(0,
                        ViewGroup.LayoutParams.WRAP_CONTENT,
                        60));
                tvValue.setTextSize(22);
                tvValue.setTextColor(getResources().getColor(R.color.colorAccent));
                tvValue.setText(entry.getValue());

                LinearLayout llh = new LinearLayout(context);
                llh.setOrientation(LinearLayout.HORIZONTAL);
                llh.setPadding(20, 20, 20, 20);
                llh.addView(tvKey);
                llh.addView(tvValue);

                covidDetailsLinearLayout.addView(llh);

                View line = new View(context);
                line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
                line.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                covidDetailsLinearLayout.addView(line);
            }
        }
    }
}