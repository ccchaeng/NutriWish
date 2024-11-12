// SupplementDetailFragment.java
package com.example.nutriwish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class SupplementDetailFragment extends Fragment {

    private String supplementName;
    private String benefits;
    private String usage;
    private String precautions;
    private boolean isFavorite;

    private int currentIndex;
    private int minIndex;
    private int maxIndex;
    private List<Supplement> supplements;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplement_detail, container, false);

        // Back button setup
        Button backButton = view.findViewById(R.id.button_back);
        backButton.setOnClickListener(v -> getParentFragmentManager().popBackStack());

        // Retrieve supplement info from the bundle
        Bundle bundle = getArguments();
        if (bundle != null) {
            supplements = (List<Supplement>) bundle.getSerializable("supplements");
            currentIndex = bundle.getInt("index", 0);
            minIndex = bundle.getInt("minIndex", 0);
            maxIndex = bundle.getInt("maxIndex", supplements.size() - 1);

            // Set current supplement details
            Supplement currentSupplement = supplements.get(currentIndex);
            supplementName = currentSupplement.getName();
            benefits = currentSupplement.getBenefits();
            usage = currentSupplement.getUsage();
            precautions = currentSupplement.getPrecautions();
            isFavorite = currentSupplement.isFavorite();
        }

        // View references
        TextView supplementTitle = view.findViewById(R.id.supplement_title);
        ImageView supplementImage = view.findViewById(R.id.supplement_image);
        TextView textContent = view.findViewById(R.id.text_content);
        Button buttonBenefits = view.findViewById(R.id.button_benefits);
        Button buttonUsage = view.findViewById(R.id.button_usage);
        Button buttonPrecautions = view.findViewById(R.id.button_precautions);
        Button buttonPrevious = view.findViewById(R.id.button_previous);
        Button buttonNext = view.findViewById(R.id.button_next);
        ToggleButton favoriteToggle = view.findViewById(R.id.favorite_toggle);

        // Set supplement details
        supplementTitle.setText(supplementName);
        supplementImage.setImageResource(R.drawable.supplement);  // Set a default image
        favoriteToggle.setChecked(isFavorite);

        // Benefits button listener
        buttonBenefits.setOnClickListener(v -> {
            textContent.setText(benefits);
            textContent.setVisibility(View.VISIBLE);
        });

        // Usage button listener
        buttonUsage.setOnClickListener(v -> {
            textContent.setText(usage);
            textContent.setVisibility(View.VISIBLE);
        });

        // Precautions button listener
        buttonPrecautions.setOnClickListener(v -> {
            textContent.setText(precautions);
            textContent.setVisibility(View.VISIBLE);
        });

        // Previous button listener
        buttonPrevious.setOnClickListener(v -> {
            if (currentIndex > minIndex) {
                openSupplementDetail(currentIndex - 1);
            } else {
                textContent.setText("첫 번째 영양제입니다.");
                textContent.setVisibility(View.VISIBLE);
            }
        });

        // Next button listener
        buttonNext.setOnClickListener(v -> {
            if (currentIndex < maxIndex) {
                openSupplementDetail(currentIndex + 1);
            } else {
                textContent.setText("마지막 영양제입니다.");
                textContent.setVisibility(View.VISIBLE);
            }
        });

        // Favorite toggle button listener
        favoriteToggle.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (supplements != null && currentIndex < supplements.size()) {
                Supplement currentSupplement = supplements.get(currentIndex);
                currentSupplement.setFavorite(isChecked);
            }
        });

        return view;
    }

    // Open the details of another supplement in the list by updating the fragment
    private void openSupplementDetail(int index) {
        SupplementDetailFragment detailFragment = new SupplementDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("supplements", (ArrayList<Supplement>) supplements);
        bundle.putInt("index", index);
        bundle.putInt("minIndex", minIndex);
        bundle.putInt("maxIndex", maxIndex);
        detailFragment.setArguments(bundle);

        getParentFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, detailFragment)
                .addToBackStack(null)
                .commit();
    }
}
