package com.example.nutriwish;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;


public class CategorySlideFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_slide, container, false);

        ViewPager2 viewPager = view.findViewById(R.id.view_pager);
        TabLayout tabLayout = view.findViewById(R.id.tab_layout);

        // ViewPager Adapter 설정
        CategoryPagerAdapter adapter = new CategoryPagerAdapter(this);
        viewPager.setAdapter(adapter);

        // TabLayout과 ViewPager 연결
        new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            //텍스트 설정
            TextView customTextView = (TextView) LayoutInflater.from(getContext())
                    .inflate(R.layout.custom_tab_text, null);

            switch (position) {
                case 0:
                    customTextView.setText("건강");
                    break;
                case 1:
                    customTextView.setText("신체 관리");
                    break;
                case 2:
                    customTextView.setText("기타");
                    break;
            }

            // 탭에 커스텀 뷰 설정
            tab.setCustomView(customTextView);
        }).attach();

        return view;
    }
}
