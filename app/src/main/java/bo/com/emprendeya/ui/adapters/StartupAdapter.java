package bo.com.emprendeya.ui.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import bo.com.emprendeya.R;
import bo.com.emprendeya.model.Startup;
import bo.com.emprendeya.ui.callback.StartupCallback;
import bo.com.emprendeya.ui.viewholder.StartupViewHolder;

public class StartupAdapter extends RecyclerView.Adapter<StartupViewHolder> {

    private List<Startup> startups;
    private LayoutInflater inflater;

    private StartupCallback callback;

    public StartupAdapter(List<Startup> startups, Context context) {
        this.startups = startups;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public StartupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = this.inflater.inflate(R.layout.startup_list_item, parent, false);
        return new StartupViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull StartupViewHolder holder, int position) {
        Startup startup = startups.get(position);
        holder.nameTextView.setText(startup.getDisplayName());
        holder.addressTextView.setText(startup.getAddress());
        Picasso.get().load(startup.getCoverPhoto()).into(holder.coverImageView);
        holder.itemView.setOnClickListener(view -> {
            if (callback != null) {
                callback.onStartupClicked(startup);
            }
        });
    }

    @Override
    public int getItemCount() {
        return startups.size();
    }

    public void updateItems(List<Startup> startups) {
        this.startups = startups;
        notifyDataSetChanged();
    }

    public void setCallback(StartupCallback callback) {
        this.callback = callback;
    }
}
