package com.example.nutriwish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CategoryFragment extends Fragment {

    public CategoryFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        // 카테고리별 버튼 클릭 시 새로운 프래그먼트를 열도록 설정
        Button btnImmunity = view.findViewById(R.id.btn_immunity);
        Button btnFatigue = view.findViewById(R.id.btn_fatigue);
        Button btnJoint = view.findViewById(R.id.btn_joint);
        Button btnDigestion = view.findViewById(R.id.btn_digestion);
        Button btnSkin = view.findViewById(R.id.btn_skin);
        Button btnBrain = view.findViewById(R.id.btn_brain);
        Button btnEye = view.findViewById(R.id.btn_eye);
        Button btnHeart = view.findViewById(R.id.btn_heart);
        Button btnDiet = view.findViewById(R.id.btn_diet);
        Button btnSleep = view.findViewById(R.id.btn_sleep);

        // 각 버튼 클릭 시 새로운 프래그먼트로 이동하며 카테고리 정보를 전달
        btnImmunity.setOnClickListener(v -> openCategoryDetailFragment("면역 강화"));
        btnFatigue.setOnClickListener(v -> openCategoryDetailFragment("피로 회복"));
        btnJoint.setOnClickListener(v -> openCategoryDetailFragment("관절 건강"));
        btnDigestion.setOnClickListener(v -> openCategoryDetailFragment("소화 개선"));
        btnSkin.setOnClickListener(v -> openCategoryDetailFragment("피부 & 모발 건강"));
        btnBrain.setOnClickListener(v -> openCategoryDetailFragment("두뇌 & 집중력 강화"));
        btnEye.setOnClickListener(v -> openCategoryDetailFragment("눈 건강"));
        btnHeart.setOnClickListener(v -> openCategoryDetailFragment("심장 & 혈관 건강"));
        btnDiet.setOnClickListener(v -> openCategoryDetailFragment("다이어트 & 체중 관리"));
        btnSleep.setOnClickListener(v -> openCategoryDetailFragment("숙면 & 스트레스 완화"));

        return view;
    }

    // 새로운 프래그먼트를 열고, 선택된 카테고리 정보를 전달하는 메서드
    private void openCategoryDetailFragment(String categoryName) {
        CategoryDetailFragment detailFragment = new CategoryDetailFragment();

        // 선택된 카테고리 정보를 Bundle을 통해 전달
        Bundle bundle = new Bundle();
        bundle.putString("category", categoryName);
        detailFragment.setArguments(bundle);

        // 새로운 프래그먼트로 교체
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.addToBackStack(null);  // 뒤로 가기 버튼을 눌렀을 때 돌아갈 수 있도록
        transaction.commit();
    }
}
