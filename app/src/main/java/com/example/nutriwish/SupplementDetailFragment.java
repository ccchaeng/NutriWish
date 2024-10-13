package com.example.nutriwish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SupplementDetailFragment extends Fragment {

    private String selectedSupplement;

    public SupplementDetailFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_supplement_detail, container, false);

        // 선택된 영양제 정보를 받아옴
        Bundle bundle = getArguments();
        if (bundle != null) {
            selectedSupplement = bundle.getString("supplement");
        }

        // 선택된 영양제 이름을 화면에 표시
        TextView supplementTitle = view.findViewById(R.id.supplement_title);
        supplementTitle.setText(selectedSupplement + " 정보");

        // 여기서 해당 영양제에 대한 세부 정보를 추가로 표시할 수 있음
        TextView supplementInfo = view.findViewById(R.id.supplement_info);
        supplementInfo.setText(selectedSupplement + "의 자세한 정보가 여기 표시됩니다.");

        return view;
    }
}
