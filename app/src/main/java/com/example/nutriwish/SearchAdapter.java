package com.example.nutriwish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Map;

public class SearchAdapter extends ArrayAdapter<String> {

    private final List<String> supplements;
    private final Map<String, Supplement> supplementData; // Supplement data for favorite status
    private final OnItemClickListener onItemClickListener;
    private final OnFavoriteToggleListener onFavoriteToggleListener;

    public interface OnItemClickListener {
        void onItemClick(String supplementName);
    }

    public interface OnFavoriteToggleListener {
        void onFavoriteToggle(String supplementName, boolean isFavorite);
    }

    public SearchAdapter(Context context, List<String> supplements, Map<String, Supplement> supplementData,
                         OnItemClickListener itemListener, OnFavoriteToggleListener favoriteToggleListener) {
        super(context, 0, supplements);
        this.supplements = supplements;
        this.supplementData = supplementData;
        this.onItemClickListener = itemListener;
        this.onFavoriteToggleListener = favoriteToggleListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_supplement, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.supplementNameText = convertView.findViewById(R.id.supplementNameText);
            viewHolder.favoriteToggleButton = convertView.findViewById(R.id.favoriteToggleButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String supplementName = getItem(position);
        viewHolder.supplementNameText.setText(supplementName);

        // Set ToggleButton state based on whether the supplement is favorited
        Supplement supplement = supplementData.get(supplementName);
        if (supplement != null) {
            viewHolder.favoriteToggleButton.setChecked(supplement.isFavorite());
        }

        // Click listener for the item itself
        convertView.setOnClickListener(v -> {
            if (supplementName != null) {
                onItemClickListener.onItemClick(supplementName);
            }
        });

        // Toggle favorite status when ToggleButton is clicked
        viewHolder.favoriteToggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (supplementName != null && supplement != null) {
                supplement.setFavorite(isChecked); // Update favorite status
                onFavoriteToggleListener.onFavoriteToggle(supplementName, isChecked); // Notify fragment
            }
        });

        return convertView;
    }

    // ViewHolder class for optimized view reuse
    private static class ViewHolder {
        TextView supplementNameText;
        ToggleButton favoriteToggleButton;
    }
}
