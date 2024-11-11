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

    // ViewHolder 패턴을 사용하기 위한 클래스
    private static class ViewHolder {
        TextView textView;
        ImageButton favoriteButton;
    }

    public interface OnItemClickListener {
        void onItemClick(String supplementName);
    }

    public interface OnFavoriteClickListener {
        void onFavoriteClick(String supplementName);
    }

    public SearchAdapter(Context context, List<String> suggestions, OnItemClickListener itemListener, OnFavoriteClickListener favoriteListener) {
        super(context, 0, suggestions);
        this.onItemClickListener = itemListener;
        this.onFavoriteClickListener = favoriteListener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_supplement, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.text1);
            viewHolder.favoriteButton = convertView.findViewById(R.id.favoriteButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String suggestion = getItem(position);
        viewHolder.textView.setText(suggestion);

        // 클릭 이벤트 처리
        convertView.setOnClickListener(v -> {
            if (suggestion != null) {
                onItemClickListener.onItemClick(suggestion);
            }
        });

        // 즐겨찾기 버튼 클릭 이벤트
        viewHolder.favoriteButton.setOnClickListener(v -> {
            if (suggestion != null) {
                onFavoriteClickListener.onFavoriteClick(suggestion);
            }
        });

        return convertView;
    }
}
