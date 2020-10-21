package bo.com.emprendeya.preparation.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bo.com.emprendeya.preparation.R;
import bo.com.emprendeya.preparation.models.Startup;
import bo.com.emprendeya.preparation.ui.callback.StartupCallback;
import bo.com.emprendeya.preparation.ui.viewholder.StartupViewHolder;

public class StartupAdapter extends RecyclerView.Adapter<StartupViewHolder> {

    private List<Startup> startups;
    private LayoutInflater inflater;
    private StartupCallback startupCallback;

    public StartupAdapter(List<Startup> startups, Context context) {
        this.startups = startups;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StartupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.startup_list_item, null);
        return new StartupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StartupViewHolder holder, int position) {
        final Startup startup = this.startups.get(position);
        holder.nameTextView.setText(startup.getDisplayName());
        Picasso.get().load(startup.getCoverPhoto()).into(holder.coverImageView);
        holder.addressTextView.setText(startup.getAddress());
        holder.itemView.setOnClickListener(view -> {
            if (startupCallback != null) {
                startupCallback.onStartupClick(startup);
            }
        });
    }

    public void updateItems(List<Startup> items) {
        this.startups = items;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return startups.size();
    }

    public void setStartupCallback(StartupCallback startupCallback) {
        this.startupCallback = startupCallback;
    }
}
