package com.example.nutriwish;

public class CalendarTaskItem {
    private String taskName;
    private String taskTime;
    private String taskMemo;

    public CalendarTaskItem(String taskName, String taskTime, String taskMemo) {
        this.taskName = taskName;
        this.taskTime = taskTime;
        this.taskMemo = taskMemo;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    public String getTaskMemo() {
        return taskMemo;
    }

    public void setTaskMemo(String taskMemo) {
        this.taskMemo = taskMemo;
    }
}