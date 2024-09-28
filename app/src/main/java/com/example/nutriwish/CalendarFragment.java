package com.example.nutriwish;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class CalendarFragment extends Fragment {

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Calendar 프래그먼트의 레이아웃을 inflate합니다.
        return inflater.inflate(R.layout.fragment_calendar, container, false);
    }
}
