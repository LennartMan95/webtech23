package de.htwberlin.webtech23.web.api;

public class Task {

    private long id;
    private String taskName;
    private boolean taskDone = false;

    public Task(long id, String taskName, boolean taskDone) {
        this.id = id;
        this.taskName = taskName;

    }

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }
}
