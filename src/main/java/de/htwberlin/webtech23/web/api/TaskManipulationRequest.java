package de.htwberlin.webtech23.web.api;

public class TaskManipulationRequest {

    private String taskName;
    private String taskDescription;
    private boolean taskDone = false;

    public TaskManipulationRequest(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public TaskManipulationRequest() {
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() { return taskDescription; }

    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }


}
