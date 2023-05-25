package de.htwberlin.webtech23.web.api;

public class TaskManipulationRequest {

    private String taskName;
    private boolean taskDone = false;

    public TaskManipulationRequest(String taskName) {
        this.taskName = taskName;

    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }


}
