package com.example.nutriwish;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SearchAdapter extends ArrayAdapter<String> {

    private final OnItemClickListener onItemClickListener;
    private Set<String> favoriteSupplements = new HashSet<>(); // 즐겨찾기 데이터

    // ViewHolder 패턴
    private static class ViewHolder {
        TextView textView;
        Button toggleFavoriteButton;
    }

    public interface OnItemClickListener {
        void onItemClick(String supplementName);
    }

    public SearchAdapter(Context context, List<String> suggestions, OnItemClickListener listener) {
        super(context, 0, suggestions);
        this.onItemClickListener = listener;
    }

    // 즐겨찾기 데이터 설정
    public void setFavoriteSupplements(Set<String> favoriteSupplements) {
        this.favoriteSupplements = favoriteSupplements;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // 새로운 레이아웃을 inflate
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_result_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(R.id.search_result_text);
            viewHolder.toggleFavoriteButton = convertView.findViewById(R.id.favorite_toggle_button);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String suggestion = getItem(position);
        viewHolder.textView.setText(suggestion);

        // 버튼 및 배경 색상 업데이트
        updateButtonColorAndText(viewHolder, suggestion);

        // 버튼 클릭 이벤트 처리
        viewHolder.toggleFavoriteButton.setOnClickListener(v -> {
            if (favoriteSupplements.contains(suggestion)) {
                favoriteSupplements.remove(suggestion);
            } else {
                favoriteSupplements.add(suggestion);
            }
            updateButtonColorAndText(viewHolder, suggestion); // 상태 업데이트
        });

        // 항목 클릭 이벤트 처리
        convertView.setOnClickListener(v -> {
            if (suggestion != null) {
                onItemClickListener.onItemClick(suggestion);
            }
        });

        return convertView;
    }

    // 버튼 상태와 색상을 업데이트하는 메서드
    private void updateButtonColorAndText(ViewHolder viewHolder, String suggestion) {
        if (favoriteSupplements.contains(suggestion)) {
            viewHolder.toggleFavoriteButton.setText("즐겨찾기 해제");
            viewHolder.toggleFavoriteButton.setBackgroundColor(Color.YELLOW); // 버튼 배경색 노란색
            viewHolder.toggleFavoriteButton.setTextColor(Color.BLACK); // 버튼 텍스트 색상
        } else {
            viewHolder.toggleFavoriteButton.setText("즐겨찾기 추가");
            viewHolder.toggleFavoriteButton.setBackgroundColor(Color.LTGRAY); // 기본 버튼 배경색
            viewHolder.toggleFavoriteButton.setTextColor(Color.BLACK); // 버튼 텍스트 색상
        }
    }

    public Set<String> getFavoriteSupplements() {
        return favoriteSupplements;
    }
}
