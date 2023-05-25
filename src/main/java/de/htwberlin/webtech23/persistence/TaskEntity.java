package de.htwberlin.webtech23.persistence;


import jakarta.persistence.*;

@Entity(name = "persons")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "task_Name", nullable = false)
    private String taskName;
    @Column(name = "is_taskDone")
    private boolean taskDone = false;

    public TaskEntity(String taskName) {
        this.taskName = taskName;
    }

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

    protected TaskEntity() {}
}
