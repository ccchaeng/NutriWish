package com.example.nutriwish;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DigestionImprovementFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_digestion_improvement, container, false);

        // Back 버튼을 찾아 클릭 리스너 추가
        TextView backButton = view.findViewById(R.id.back);
        backButton.setOnClickListener(v -> {
            // 뒤로가기 구현: FragmentManager를 사용해 이전 프래그먼트로 이동
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            if (fragmentManager.getBackStackEntryCount() > 0) {
                fragmentManager.popBackStack(); // 카테고리 프래그먼트로 돌아가기
            } else {
                // 만약 백스택에 프래그먼트가 없으면 액티비티 종료
                requireActivity().onBackPressed();
            }
        });

        return view;
    }
}
