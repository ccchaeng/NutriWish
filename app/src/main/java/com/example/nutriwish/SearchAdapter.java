package com.example.nutriwish;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class SearchAdapter extends ArrayAdapter<String> {

    private LayoutInflater inflater;

    public SearchAdapter(Context context, List<String> suggestions) {
        super(context, android.R.layout.simple_dropdown_item_1line, suggestions);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);
            holder = new ViewHolder();
            holder.suggestionTextView = convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // 현재 위치에 해당하는 연관 검색어 표시, null 방어적 처리 추가
        String suggestion = getItem(position);
        if (suggestion != null) {
            holder.suggestionTextView.setText(suggestion);
        } else {
            holder.suggestionTextView.setText("No suggestion available");  // 기본값 설정
        }

        return convertView;
    }

    // 어댑터의 데이터를 업데이트하는 메서드
    public void updateData(List<String> newSuggestions) {
        clear();  // 기존 데이터를 지우고
        if (newSuggestions != null) {
            addAll(newSuggestions);  // 새 데이터로 대체
        }
        // notifyDataSetChanged()는 addAll()이 자동으로 호출합니다.
    }

    private static class ViewHolder {
        TextView suggestionTextView;
    }
}
