package com.example.nutriwish;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    private EditText etName, etBirthDate, etAge;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private Button btnEdit, btnSave;

    public UserFragment() {
        // 필수 빈 생성자
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 프래그먼트의 레이아웃을 inflate합니다.
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // EditText 필드 초기화
        etName = view.findViewById(R.id.etName);
        etBirthDate = view.findViewById(R.id.etBirthDate);
        etAge = view.findViewById(R.id.etAge);

        // 성별 선택을 위한 RadioGroup과 RadioButton 초기화
        rgGender = view.findViewById(R.id.rgGender);
        rbMale = view.findViewById(R.id.rbMale);
        rbFemale = view.findViewById(R.id.rbFemale);

        // 버튼 초기화
        btnEdit = view.findViewById(R.id.btnEdit);
        btnSave = view.findViewById(R.id.btnSave);

        // 필드에 기본값 설정
        etName.setText("조용수");
        etBirthDate.setText("2000-07-31");
        etAge.setText("25");

        // 남성 라디오 버튼을 기본 선택 (남)
        rbMale.setChecked(true);

        // 초기에는 필드와 저장 버튼 비활성화
        enableEditTexts(false);
        btnSave.setEnabled(false);

        // 수정 버튼 클릭 시 필드 활성화
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditTexts(true);
                btnSave.setEnabled(true);  // 저장 버튼 활성화
            }
        });

        // 저장 버튼 클릭 시 데이터 저장 및 필드 비활성화
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
                enableEditTexts(false);
                btnSave.setEnabled(false);  // 저장 후 저장 버튼 비활성화
            }

            private void saveUserData() {
                // 입력 필드에서 값 가져오기
                String name = etName.getText().toString();
                String birthDate = etBirthDate.getText().toString();
                String age = etAge.getText().toString();
                String gender = rbMale.isChecked() ? "남" : "여";  // 선택된 성별 가져오기

                // 여기에 데이터를 데이터베이스 또는 SharedPreferences에 저장하는 로직을 추가할 수 있습니다.
                // 현재는 값을 출력만 합니다.
                System.out.println("이름: " + name);
                System.out.println("생년월일: " + birthDate);
                System.out.println("나이: " + age);
                System.out.println("성별: " + gender);
            }
        });

        // 시스템 바(네비게이션 바, 상태 바) 처리
        ViewCompat.setOnApplyWindowInsetsListener(view.findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        return view;
    }

    // EditText 및 RadioButton 필드 활성화/비활성화
    private void enableEditTexts(boolean enable) {
        etName.setEnabled(enable);
        etBirthDate.setEnabled(enable);
        etAge.setEnabled(enable);

        // 모든 RadioButton의 활성화/비활성화
        for (int i = 0; i < rgGender.getChildCount(); i++) {
            rgGender.getChildAt(i).setEnabled(enable);
        }
    }
}
