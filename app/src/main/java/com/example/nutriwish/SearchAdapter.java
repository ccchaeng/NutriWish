package com.example.nutriwish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<String> {

    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String supplementName);
    }

    public SearchAdapter(Context context, List<String> suggestions, OnItemClickListener listener) {
        super(context, 0, suggestions);
        this.onItemClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView textView = convertView.findViewById(android.R.id.text1);
        String suggestion = getItem(position);
        textView.setText(suggestion);

        // 클릭 이벤트 처리
        convertView.setOnClickListener(v -> {
            if (onItemClickListener != null && suggestion != null) {
                onItemClickListener.onItemClick(suggestion);
            }
        });

        return convertView;
    }
}
