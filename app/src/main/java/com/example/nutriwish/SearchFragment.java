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

import com.example.nutriwish.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    private EditText searchInput;
    private Button searchButton;
    private ListView resultListView;

    // 영양제 연관 검색어 리스트
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

        // 영양제 연관 검색어 리스트 초기화
        suggestions = new ArrayList<>();
        suggestions.add("Vitamin C");
        suggestions.add("Omega-3");
        suggestions.add("Calcium");
        suggestions.add("Probiotics");
        suggestions.add("Iron");
        suggestions.add("Collagen");
        suggestions.add("Vitamin D");
        suggestions.add("Zinc");
        suggestions.add("Magnesium");
        suggestions.add("Multivitamin");
        suggestions.add("Folic Acid");
        suggestions.add("B-Complex");
        suggestions.add("Vitamin B12");
        suggestions.add("Glucosamine");
        suggestions.add("CoQ10");
        suggestions.add("Fish Oil");
        suggestions.add("Lutein");
        suggestions.add("Echinacea");
        suggestions.add("Ginseng");
        suggestions.add("Turmeric");
        suggestions.add("Green Tea Extract");
        suggestions.add("Probiotic Acidophilus");
        suggestions.add("Amino Acids");
        suggestions.add("Ashwagandha");
        suggestions.add("Spirulina");
        suggestions.add("Whey Protein");
        suggestions.add("Biotin");
        suggestions.add("Hyaluronic Acid");
        suggestions.add("Milk Thistle");

        // 처음엔 빈 리스트로 어댑터 초기화 (리스트가 보이지 않도록)
        searchAdapter = new SearchAdapter(getContext(), new ArrayList<>());
        resultListView.setAdapter(searchAdapter);  // 어댑터를 ListView에 연결

        // 검색 버튼 클릭 이벤트 처리
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = searchInput.getText().toString();
                filterSearchResults(query);  // 검색어에 맞는 결과 필터링
            }
        });

        return view;
    }

    // 검색어에 맞는 결과를 필터링하는 메서드
    private void filterSearchResults(String query) {
        List<String> filteredResults = new ArrayList<>();

        if (!query.isEmpty()) {
            for (String suggestion : suggestions) {
                // 검색어가 연관 검색어에 포함되어 있으면 결과 리스트에 추가
                if (suggestion.toLowerCase().contains(query.toLowerCase())) {
                    filteredResults.add(suggestion);
                }
            }
        }

        // 어댑터의 데이터를 갱신 (필터링된 검색어 리스트로 갱신)
        searchAdapter.updateData(filteredResults);
    }
}
