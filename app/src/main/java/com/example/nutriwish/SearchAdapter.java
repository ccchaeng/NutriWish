package com.example.nutriwish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<String> {

    private final OnItemClickListener onItemClickListener;
    private final OnFavoriteClickListener onFavoriteClickListener;

    // ViewHolder pattern to optimize list item performance
    private static class ViewHolder {
        TextView textView;
        ImageButton favoriteButton;
    }

    // Listener interface for item clicks
    public interface OnItemClickListener {
        void onItemClick(String supplementName);
    }

    // Listener interface for favorite button clicks
    public interface OnFavoriteClickListener {
        void onFavoriteClick(String supplementName);
    }

    // Adapter constructor
    public SearchAdapter(Context context, List<String> suggestions, OnItemClickListener itemListener, OnFavoriteClickListener favoriteListener) {
        super(context, 0, suggestions);
        this.onItemClickListener = itemListener;
        this.onFavoriteClickListener = favoriteListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // Inflate the item layout and initialize the ViewHolder
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_supplement, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.text1);  // Update to match actual ID
            viewHolder.favoriteButton = convertView.findViewById(R.id.favoriteButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String supplementName = getItem(position);
        viewHolder.textView.setText(supplementName);

        // Set up click listener for the entire item
        convertView.setOnClickListener(v -> {
            if (supplementName != null) {
                onItemClickListener.onItemClick(supplementName);
            }
        });

        // Set up click listener for the favorite button
        viewHolder.favoriteButton.setOnClickListener(v -> {
            if (supplementName != null) {
                onFavoriteClickListener.onFavoriteClick(supplementName);
            }
        });

        return convertView;
    }
}
