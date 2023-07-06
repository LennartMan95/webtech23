package de.htwberlin.webtech23.web.api;

public class Task {

    private long id;
    private String taskName;
    private String taskDescription;
    private boolean taskDone = false;

    public Task(long id, String taskName, String taskDescription) {
        this.id = id;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
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

    public String getTaskDescription() { return taskDescription; }

    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }
}
