package com.example.nutriwish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class CategoryDetailFragment extends Fragment {

    private String selectedCategory;

    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);

        // 선택된 카테고리 정보를 받아옴
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedCategory = bundle.getString("category");
        }

        // 카테고리에 따라 다른 버튼 목록을 동적으로 생성
        LinearLayout buttonContainer = view.findViewById(R.id.button_container);

        if (selectedCategory != null) {
            switch (selectedCategory) {
                case "면역 강화":
                    addButtons(buttonContainer, new String[]{"영양제 1", "영양제 2", "영양제 3"});
                    break;
                case "피로 회복":
                    addButtons(buttonContainer, new String[]{"영양제 4", "영양제 5", "영양제 6"});
                    break;
                case "관절 건강":
                    addButtons(buttonContainer, new String[]{"영양제 7", "영양제 8"});
                    break;
                case "소화 개선":
                    addButtons(buttonContainer, new String[]{"영양제 9", "영양제 10"});
                    break;
                // 각 카테고리별로 다른 목록 추가
                default:
                    addButtons(buttonContainer, new String[]{"기본 영양제 1", "기본 영양제 2"});
                    break;
            }
        }

        return view;
    }

    // 동적으로 버튼을 추가하고, 버튼 클릭 시 새로운 프래그먼트로 이동
    private void addButtons(LinearLayout container, String[] items) {
        for (String item : items) {
            Button button = new Button(getContext());
            button.setText(item);

            // 각 영양제 버튼을 클릭 시 새로운 프래그먼트를 열도록 설정
            button.setOnClickListener(v -> openSupplementDetailFragment(item));

            container.addView(button);
        }
    }

    // 영양제 버튼 클릭 시 새로운 프래그먼트를 여는 메서드
    private void openSupplementDetailFragment(String supplementName) {
        SupplementDetailFragment detailFragment = new SupplementDetailFragment();

        // 선택된 영양제 정보를 Bundle을 통해 전달
        Bundle bundle = new Bundle();
        bundle.putString("supplement", supplementName);
        detailFragment.setArguments(bundle);

        // 새로운 프래그먼트로 교체
        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.addToBackStack(null);  // 뒤로 가기 버튼을 눌렀을 때 돌아갈 수 있도록
        transaction.commit();
    }
}
