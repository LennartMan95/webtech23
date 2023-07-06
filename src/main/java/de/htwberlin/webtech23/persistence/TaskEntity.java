package de.htwberlin.webtech23.persistence;


import jakarta.persistence.*;

@Entity(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "task_description", nullable = false)
    private String taskDescription;

    @Column(name = "is_task_done")
    private boolean taskDone = false;

    public TaskEntity(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    protected TaskEntity() {}

    public long getId() {
        return id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String firstName) {
        this.taskName = firstName;
    }

    public boolean isTaskDone() {
        return taskDone;
    }

    public void setTaskDone(boolean taskDone) {
        this.taskDone = taskDone;
    }

    public String getTaskDescription() { return taskDescription; }

    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }
}
