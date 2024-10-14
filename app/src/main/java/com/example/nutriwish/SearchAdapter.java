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

    // ViewHolder 패턴을 사용하기 위한 클래스
    private static class ViewHolder {
        TextView textView;
    }

    public interface OnItemClickListener {
        void onItemClick(String supplementName);
    }

    public SearchAdapter(Context context, List<String> suggestions, OnItemClickListener listener) {
        super(context, 0, suggestions);
        this.onItemClickListener = listener;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;

        if (convertView == null) {
            // convertView가 null이면 새로 생성하고 ViewHolder를 연결
            convertView = LayoutInflater.from(getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.textView = convertView.findViewById(android.R.id.text1);
            convertView.setTag(viewHolder);
        } else {
            // convertView가 재사용되는 경우 ViewHolder를 가져옴
            viewHolder = (ViewHolder) convertView.getTag();
        }

        String suggestion = getItem(position);
        viewHolder.textView.setText(suggestion);

        // 클릭 이벤트 처리 (null 체크를 간결하게)
        convertView.setOnClickListener(v -> {
            if (suggestion != null) {
                onItemClickListener.onItemClick(suggestion);
            }
        });

        return convertView;
    }
}
