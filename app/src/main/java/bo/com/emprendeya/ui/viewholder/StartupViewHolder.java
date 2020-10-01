package bo.com.emprendeya.ui.viewholder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import bo.com.emprendeya.R;

public class StartupViewHolder extends RecyclerView.ViewHolder {

    public ImageView coverImageView;
    public TextView nameTextView;
    public TextView addressTextView;

    public StartupViewHolder(@NonNull View itemView) {
        super(itemView);
        this.coverImageView = itemView.findViewById(R.id.coverImageView);
        this.nameTextView = itemView.findViewById(R.id.nameTextView);
        this.addressTextView = itemView.findViewById(R.id.addressTextView);
    }
}
