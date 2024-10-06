package com.example.nutriwish;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment {

    private EditText etName, etBirthDate, etId, etPassword;
    private RadioGroup rgGender;
    private RadioButton rbMale, rbFemale;
    private Button btnEdit, btnSave, btnLogout;
    private ImageView profileImage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, container, false);

        // 프로필 이미지 초기화
        profileImage = view.findViewById(R.id.profileImage);

        // EditText 필드 초기화
        etName = view.findViewById(R.id.etName);
        etBirthDate = view.findViewById(R.id.etBirthDate);
        etId = view.findViewById(R.id.etId);
        etPassword = view.findViewById(R.id.etPassword);

        // 성별 선택을 위한 RadioGroup과 RadioButton 초기화
        rgGender = view.findViewById(R.id.rgGender);
        rbMale = view.findViewById(R.id.rbMale);
        rbFemale = view.findViewById(R.id.rbFemale);

        // 버튼 초기화
        btnEdit = view.findViewById(R.id.btnEdit);
        btnSave = view.findViewById(R.id.btnSave);
        btnLogout = view.findViewById(R.id.btnLogout);

        // 성별 선택 리스너 추가
        rgGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbMale) {
                    // 남자 선택 시 프로필 이미지를 변경
                    profileImage.setImageResource(R.drawable.male_profile);  // 남자 이미지 파일
                } else if (checkedId == R.id.rbFemale) {
                    // 여자 선택 시 프로필 이미지를 변경
                    profileImage.setImageResource(R.drawable.female_profile);  // 여자 이미지 파일
                }
            }
        });

        // 초기에는 필드와 저장 버튼 비활성화
        enableEditTexts(false);
        btnSave.setVisibility(View.GONE);
        btnSave.setEnabled(false);

        // 수정 버튼 클릭 시 필드 활성화 및 저장 버튼 표시
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enableEditTexts(true);
                btnSave.setVisibility(View.VISIBLE);
                btnSave.setEnabled(true);
                btnEdit.setVisibility(View.GONE);
            }
        });

        // 저장 버튼 클릭 시 데이터 저장 및 필드 비활성화
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserData();
                enableEditTexts(false);
                btnSave.setVisibility(View.GONE);
                btnSave.setEnabled(false);
                btnEdit.setVisibility(View.VISIBLE);
            }
        });

        // 로그아웃 버튼 클릭 시 로그인 화면으로 이동
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        return view;
    }

    private void enableEditTexts(boolean enable) {
        etName.setEnabled(enable);
        etBirthDate.setEnabled(enable);
        etId.setEnabled(enable);
        etPassword.setEnabled(enable);
        for (int i = 0; i < rgGender.getChildCount(); i++) {
            rgGender.getChildAt(i).setEnabled(enable);
        }
    }

    private void saveUserData() {
        String name = etName.getText().toString();
        String birthDate = etBirthDate.getText().toString();
        String id = etId.getText().toString();
        String password = etPassword.getText().toString();
        String gender = rbMale.isChecked() ? "남" : "여";

        System.out.println("이름: " + name);
        System.out.println("생년월일: " + birthDate);
        System.out.println("아이디: " + id);
        System.out.println("비밀번호: " + password);
        System.out.println("성별: " + gender);
    }
}
