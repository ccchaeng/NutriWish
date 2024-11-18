// CategoryDetailFragment.java
package com.example.nutriwish;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CategoryDetailFragment extends Fragment {

    private String selectedCategory;
    private Map<String, List<Supplement>> categorySupplements;

    public CategoryDetailFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category_detail, container, false);

        // 뒤로가기 버튼 설정
        Button backButton = view.findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        // 선택된 카테고리 정보를 받아옴
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedCategory = bundle.getString("category");
        }

        // 영양제 데이터를 가져옴
        categorySupplements = SupplementData.getCategorySupplements();

        // 선택한 카테고리에 따라 영양제 버튼을 동적으로 생성
        LinearLayout buttonContainer = view.findViewById(R.id.button_container);

        if (selectedCategory != null && categorySupplements.containsKey(selectedCategory)) {
            List<Supplement> supplements = categorySupplements.get(selectedCategory);
            addButtons(buttonContainer, supplements);
        }

        return view;
    }

    // 동적으로 영양제 버튼을 추가하고 클릭 시 상세 정보로 이동
    private void addButtons(LinearLayout container, List<Supplement> supplements) {
        for (int i = 0; i < supplements.size(); i++) {
            Supplement supplement = supplements.get(i);
            Button button = new Button(getContext());
            button.setText(supplement.getName());

            // 버튼 색상
            button.setBackgroundColor(Color.parseColor("#f3fbf3"));

            // 버튼 폰트
            Typeface typeface = ResourcesCompat.getFont(getContext(), R.font.gamtan);
            button.setTypeface(typeface);

            // 버튼 마진 설정
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, // 가로 크기
                    LinearLayout.LayoutParams.WRAP_CONTENT  // 세로 크기
            );
            params.setMargins(20, 20, 20, 0); // left, top, right, bottom
            button.setLayoutParams(params);




            int finalIndex = i;
            button.setOnClickListener(v -> openSupplementDetailFragment(supplements, finalIndex));

            container.addView(button);
        }
    }

    // 선택한 영양제의 상세 정보를 표시하는 프래그먼트로 전환
    private void openSupplementDetailFragment(List<Supplement> supplements, int index) {
        SupplementDetailFragment detailFragment = new SupplementDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("supplements", (ArrayList<Supplement>) supplements);
        bundle.putInt("index", index);
        detailFragment.setArguments(bundle);

        FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detailFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
