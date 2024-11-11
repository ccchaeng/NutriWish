package com.example.nutriwish;

public class CalendarTaskItem {
    private String id;
    private String taskName;
    private String taskDate;
    private String taskTime;
    private String taskMemo;

    public CalendarTaskItem() {}

    public CalendarTaskItem(String taskName, String taskDate, String taskTime, String taskMemo) {
        this.taskName = taskName;
        this.taskDate = taskDate;
        this.taskTime = taskTime;
        this.taskMemo = taskMemo;
    }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDate() { return taskDate; }

    public void setTaskDate(String taskDate) { this.taskDate = taskDate; }

    public String getTaskTime() { return taskTime; }

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
