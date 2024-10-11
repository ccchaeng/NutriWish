package com.example.nutriwish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchFragment extends Fragment {

    private EditText searchInput;
    private Button searchButton;
    private ListView resultListView;

    // 영양제 데이터를 보관
    private Map<String, Supplement> supplementData;
    private List<String> suggestions;
    private SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // View 요소 초기화
        searchInput = view.findViewById(R.id.searchInput);
        searchButton = view.findViewById(R.id.searchButton);
        resultListView = view.findViewById(R.id.resultListView);

        // 영양제 데이터를 SupplementData에서 가져옴
        supplementData = loadAllSupplements();
        suggestions = new ArrayList<>(supplementData.keySet());

        // 어댑터 초기화 및 클릭 리스너 설정
        searchAdapter = new SearchAdapter(getContext(), new ArrayList<>(), this::openSupplementDetail);
        resultListView.setAdapter(searchAdapter);

        // 검색 버튼 클릭 이벤트 처리
        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString();
            filterSearchResults(query);  // 검색어에 맞는 결과 필터링
        });

        return view;
    }

    // 모든 카테고리의 영양제를 한 번에 불러오는 메서드
    private Map<String, Supplement> loadAllSupplements() {
        Map<String, Supplement> allSupplements = new HashMap<>();
        Map<String, List<Supplement>> categorySupplements = SupplementData.getCategorySupplements();

        for (List<Supplement> supplementList : categorySupplements.values()) {
            for (Supplement supplement : supplementList) {
                allSupplements.put(supplement.getName(), supplement);
            }
        }

        return allSupplements;
    }

    // 검색어에 맞는 결과를 필터링하는 메서드
    private void filterSearchResults(String query) {
        List<String> filteredResults = new ArrayList<>();

        if (!query.isEmpty()) {
            for (String suggestion : suggestions) {
                if (suggestion.toLowerCase().contains(query.toLowerCase())) {
                    filteredResults.add(suggestion);
                }
            }
        }

        // 어댑터의 데이터를 갱신 (필터링된 검색어 리스트로 갱신)
        searchAdapter.clear();
        searchAdapter.addAll(filteredResults);
        searchAdapter.notifyDataSetChanged();
    }

    // 검색 결과 클릭 시 영양제 상세 정보를 표시하는 메서드
    private void openSupplementDetail(String supplementName) {
        Supplement supplement = supplementData.get(supplementName);
        if (supplement != null) {
            SupplementDetailFragment detailFragment = new SupplementDetailFragment();
            Bundle bundle = new Bundle();
            bundle.putString("name", supplement.getName());
            bundle.putString("benefits", supplement.getBenefits());
            bundle.putString("usage", supplement.getUsage());
            bundle.putString("precautions", supplement.getPrecautions());
            detailFragment.setArguments(bundle);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, detailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
