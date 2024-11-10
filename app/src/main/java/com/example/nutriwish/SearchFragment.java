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
    private Button searchButton, favoritesButton;
    private ListView resultListView;

    private Map<String, Supplement> supplementData;
    private List<String> suggestions;
    private SearchAdapter searchAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        // Initialize UI elements
        searchInput = view.findViewById(R.id.searchInput);
        searchButton = view.findViewById(R.id.searchButton);
        favoritesButton = view.findViewById(R.id.favoritesButton);
        resultListView = view.findViewById(R.id.resultListView);

        // Load supplement data and prepare suggestions
        supplementData = loadAllSupplements();
        suggestions = new ArrayList<>(supplementData.keySet());

        // Initialize SearchAdapter with item click and favorite toggle functionality
        searchAdapter = new SearchAdapter(getContext(), new ArrayList<>(),
                supplementName -> openSupplementDetail(supplementName),
                supplementName -> toggleFavorite(supplementName)
        );
        resultListView.setAdapter(searchAdapter);

        // Set click listeners for search and favorites buttons
        searchButton.setOnClickListener(v -> {
            String query = searchInput.getText().toString();
            filterSearchResults(query);
        });

        favoritesButton.setOnClickListener(v -> showFavorites());

        return view;
    }

    // Method to load all supplements from SupplementData class
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

    // Method to filter search results based on query
    private void filterSearchResults(String query) {
        List<String> filteredResults = new ArrayList<>();
        if (!query.isEmpty()) {
            for (String suggestion : suggestions) {
                if (suggestion.toLowerCase().contains(query.toLowerCase())) {
                    filteredResults.add(suggestion);
                }
            }
        }
        searchAdapter.clear();
        searchAdapter.addAll(filteredResults);
        searchAdapter.notifyDataSetChanged();
    }

    // Toggle the favorite status of a supplement
    private void toggleFavorite(String supplementName) {
        Supplement supplement = supplementData.get(supplementName);
        if (supplement != null) {
            supplement.setFavorite(!supplement.isFavorite());  // Toggle favorite status
        }
    }

    // Show only favorite supplements
    private void showFavorites() {
        List<String> favoriteResults = new ArrayList<>();
        for (String suggestion : suggestions) {
            Supplement supplement = supplementData.get(suggestion);
            if (supplement != null && supplement.isFavorite()) {
                favoriteResults.add(suggestion);
            }
        }
        searchAdapter.clear();
        searchAdapter.addAll(favoriteResults);
        searchAdapter.notifyDataSetChanged();
    }

    // Open supplement detail fragment when a supplement is clicked
    private void openSupplementDetail(String supplementName) {
        Supplement supplement = supplementData.get(supplementName);
        if (supplement != null) {
            SupplementDetailFragment detailFragment = new SupplementDetailFragment();
            Bundle bundle = new Bundle();

            List<Supplement> supplements = new ArrayList<>();
            supplements.add(supplement);
            bundle.putSerializable("supplements", (ArrayList<Supplement>) supplements);
            bundle.putInt("index", 0);  // Only one item selected, so index is 0
            bundle.putInt("minIndex", 0);
            bundle.putInt("maxIndex", 0);

            detailFragment.setArguments(bundle);

            FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, detailFragment);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }
}
