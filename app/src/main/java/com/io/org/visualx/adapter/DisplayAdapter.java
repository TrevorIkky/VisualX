package com.io.org.visualx.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.io.org.visualx.R;
import com.io.org.visualx.ebay.marketplace.search.v1.services.SearchItem;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DisplayAdapter extends RecyclerView.Adapter<DisplayAdapter.DisplayHolder> {

    private Context context;
    private List<SearchItem> searchItems = new ArrayList<>();

    public DisplayAdapter(Context context, List<SearchItem> searchItems) {
        this.context = context;
        this.searchItems = searchItems;
    }

    @NonNull
    @Override
    public DisplayHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_model, parent, false);
        return new DisplayHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DisplayHolder holder, int position) {
        SearchItem searchItemList = searchItems.get(position);
        Glide.with(context)
                .load(searchItemList.galleryURL)
                .into(holder.displayImage);
        holder.title.setText(searchItemList.itemId);

    }

    @Override
    public int getItemCount() {
        return searchItems.size();
    }

    public class DisplayHolder extends RecyclerView.ViewHolder {
        ImageView displayImage;
        TextView title;

        public DisplayHolder(@NonNull View itemView) {
            super(itemView);
            displayImage = itemView.findViewById(R.id.display_image);
            title = itemView.findViewById(R.id.title);
        }
    }
}
