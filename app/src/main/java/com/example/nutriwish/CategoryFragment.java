package com.example.nutriwish;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CategoryFragment extends Fragment {

    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    private ArrayList<String> categoryList;
    private static final String TAG = "CategoryFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_category);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 카테고리 리스트 초기화
        categoryList = new ArrayList<>();
        categoryList.add("1. 면역 강화");
        categoryList.add("2. 피로 회복");
        categoryList.add("3. 관절 건강");
        categoryList.add("4. 소화 개선");
        categoryList.add("5. 피부 & 모발 건강");
        categoryList.add("6. 두뇌 & 집중력 강화");
        categoryList.add("7. 눈 건강");
        categoryList.add("8. 심장 & 혈관 건강");
        categoryList.add("9. 다이어트 & 체중 관리");
        categoryList.add("10. 숙면 & 스트레스 완화");

        // 어댑터 설정 및 클릭 리스너 처리
        categoryAdapter = new CategoryAdapter(categoryList);
        categoryAdapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 디버깅을 위한 로그 추가
                Log.d(TAG, "Item clicked at position: " + position);

                // 선택한 카테고리에 맞는 Fragment로 전환
                Fragment selectedFragment = null;

                switch (position) {
                    case 0:
                        selectedFragment = new ImmunityFragment();
                        break;
                    case 1:
                        selectedFragment = new FatigueRecoveryFragment();
                        break;
                    case 2:
                        selectedFragment = new JointHealthFragment();
                        break;
                    case 3:
                        selectedFragment = new DigestionImprovementFragment();
                        break;
                    case 4:
                        selectedFragment = new SkinHairHealthFragment();
                        break;
                    case 5:
                        selectedFragment = new BrainFocusFragment();
                        break;
                    case 6:
                        selectedFragment = new EyeHealthFragment();
                        break;
                    case 7:
                        selectedFragment = new HeartVascularHealthFragment();
                        break;
                    case 8:
                        selectedFragment = new DietWeightManagementFragment();
                        break;
                    case 9:
                        selectedFragment = new SleepStressFragment();
                        break;
                }

                if (selectedFragment != null) {
                    FragmentTransaction transaction = requireFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_container, selectedFragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
            }
        });

        recyclerView.setAdapter(categoryAdapter);

        return view;
    }
}
