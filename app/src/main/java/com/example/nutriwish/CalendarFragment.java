package com.example.nutriwish;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalendarFragment extends Fragment {

    private CalendarView calendarView;
    private Button addTaskButton;
    private RecyclerView taskList;
    private CalendarTaskListAdapter taskListAdapter;
    private List<CalendarTaskItem> tasks;
    private HashMap<String, CalendarTaskItem> taskMap = new HashMap<>();  // 날짜에 따른 일정 저장
    private String selectedTime = "";  // 선택된 시간 저장

    public CalendarFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        addTaskButton = view.findViewById(R.id.add_task_button);
        taskList = view.findViewById(R.id.task_list);
        tasks = new ArrayList<>();

        taskListAdapter = new CalendarTaskListAdapter(tasks, new CalendarTaskListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CalendarTaskItem taskItem) {
                // 사용자가 저장된 일정을 클릭하면 세부 정보 다이얼로그를 띄움
                showTaskDetailsDialog(taskItem);
            }
        });

        taskList.setLayoutManager(new LinearLayoutManager(getContext()));
        taskList.setAdapter(taskListAdapter);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String selectedDate = year + "/" + (month + 1) + "/" + dayOfMonth;

                if (taskMap.containsKey(selectedDate)) {
                    CalendarTaskItem task = taskMap.get(selectedDate);
                    tasks.clear();
                    tasks.add(task);
                    taskListAdapter.notifyDataSetChanged();
                } else {
                    tasks.clear();
                    taskListAdapter.notifyDataSetChanged();
                    addTaskButton.setVisibility(View.VISIBLE);
                }

                addTaskButton.setOnClickListener(v -> {
                    showAddTaskDialog(selectedDate);
                });
            }
        });

        return view;
    }

    // 일정 추가 다이얼로그를 표시하는 메서드
    private void showAddTaskDialog(String selectedDate) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task_calendar, null);
        builder.setView(dialogView);

        EditText taskNameInput = dialogView.findViewById(R.id.task_name_input);
        TextView taskTimeDisplay = dialogView.findViewById(R.id.task_time_display);
        EditText taskMemoInput = dialogView.findViewById(R.id.task_memo_input);
        Button setTimeButton = dialogView.findViewById(R.id.set_time_button);

        setTimeButton.setOnClickListener(v -> {
            showTimePicker(taskTimeDisplay);
        });

        builder.setPositiveButton("저장", (dialog, which) -> {
            String taskName = taskNameInput.getText().toString();
            String taskMemo = taskMemoInput.getText().toString();

            if (!taskName.isEmpty() && !selectedTime.isEmpty()) {
                CalendarTaskItem newTask = new CalendarTaskItem(taskName, selectedTime, taskMemo);
                taskMap.put(selectedDate, newTask);
                tasks.clear();
                tasks.add(newTask);
                taskListAdapter.notifyDataSetChanged();
                addTaskButton.setVisibility(View.GONE);
            } else {
                Toast.makeText(getContext(), "영양제 이름과 시간을 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        });

        builder.setNegativeButton("취소", (dialog, which) -> dialog.dismiss());

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // 저장된 일정 클릭 시 다이얼로그를 표시하고 수정할 수 있는 메서드
    private void showTaskDetailsDialog(CalendarTaskItem taskItem) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_task_calendar, null);
        builder.setView(dialogView);

        EditText taskNameInput = dialogView.findViewById(R.id.task_name_input);
        TextView taskTimeDisplay = dialogView.findViewById(R.id.task_time_display);
        EditText taskMemoInput = dialogView.findViewById(R.id.task_memo_input);
        Button setTimeButton = dialogView.findViewById(R.id.set_time_button);

        taskNameInput.setText(taskItem.getTaskName());  // 기존 영양제 이름 표시
        taskTimeDisplay.setText(taskItem.getTaskTime());  // 기존 시간 표시
        taskMemoInput.setText(taskItem.getTaskMemo());  // 기존 메모 표시

        // 초기에는 수정 불가능하게 설정
        taskNameInput.setEnabled(false);
        taskMemoInput.setEnabled(false);
        setTimeButton.setEnabled(false);

        // 다이얼로그를 닫지 않고 수정 모드로 전환할 수 있는 버튼 추가
        builder.setNeutralButton("수정", null);  // '수정' 버튼을 다이얼로그가 닫히지 않게 설정

        builder.setPositiveButton("확인", (dialog, which) -> {
            dialog.dismiss();  // 읽기 모드에서 확인만 가능
        });

        builder.setNegativeButton("취소", (dialog, which) -> dialog.dismiss());

        // 다이얼로그 생성 후 수정 버튼 동작 처리
        AlertDialog dialog = builder.create();
        dialog.show();

        // 수정 버튼 클릭 시 동작 처리
        dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(v -> {
            // 수정 모드로 전환 (입력 필드 활성화)
            taskNameInput.setEnabled(true);
            taskMemoInput.setEnabled(true);
            setTimeButton.setEnabled(true);

            // 수정 완료 후 저장 버튼 처리
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setText("저장");  // 확인 버튼을 저장 버튼으로 변경

            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v1 -> {
                String updatedTaskName = taskNameInput.getText().toString();
                String updatedTaskMemo = taskMemoInput.getText().toString();
                String updatedTaskTime = taskTimeDisplay.getText().toString();  // 사용자가 수정한 시간

                if (!updatedTaskName.isEmpty() && !updatedTaskTime.isEmpty()) {
                    // 데이터를 업데이트
                    taskItem.setTaskName(updatedTaskName);
                    taskItem.setTaskTime(updatedTaskTime);
                    taskItem.setTaskMemo(updatedTaskMemo);

                    taskListAdapter.notifyDataSetChanged();  // 리스트를 갱신
                    dialog.dismiss();  // 다이얼로그 닫기
                    Toast.makeText(getContext(), "일정이 수정되었습니다.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "영양제 이름과 시간을 입력하세요.", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }


    // 타임 피커 다이얼로그를 표시하는 메서드
    private void showTimePicker(TextView taskTimeDisplay) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                (view, hourOfDay, minute) -> {
                    selectedTime = String.format("%02d:%02d", hourOfDay, minute);  // 선택한 시간 저장
                    taskTimeDisplay.setText(selectedTime);  // 선택한 시간을 표시
                }, 12, 0, true);  // 기본 값은 12:00으로 설정 (24시간 형식)
        timePickerDialog.show();
    }
}
