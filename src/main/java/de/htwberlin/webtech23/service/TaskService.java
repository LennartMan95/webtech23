package de.htwberlin.webtech23.service;

import de.htwberlin.webtech23.persistence.TaskEntity;
import de.htwberlin.webtech23.persistence.TaskRepository;
import de.htwberlin.webtech23.web.api.Task;
import de.htwberlin.webtech23.web.api.TaskManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository){
        this.taskRepository = taskRepository;
    }

    public List<Task> findAll(){
        List<TaskEntity> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Task findById(Long id){
        var taskEntity = taskRepository.findById(id);
        return taskEntity.isPresent()? transformEntity(taskEntity.get()) : null;
    }

    public Task update (Long id, TaskManipulationRequest request){
        var taskEntityOptional = taskRepository.findById(id);
        if (taskEntityOptional.isEmpty()){
            return null;
        }
        var taskEntity = taskEntityOptional.get();
        taskEntity.setTaskName(request.getTaskName());
        taskEntity.setTaskDone(request.isTaskDone());
        taskEntity = taskRepository.save(taskEntity);

        return transformEntity(taskEntity);
    }



    public Task create(TaskManipulationRequest request) {
        var taskEntity = new TaskEntity(request.getTaskName());
        taskEntity = taskRepository.save(taskEntity);
        return transformEntity(taskEntity);
    }

    public boolean deleteById(Long id) {
        if (taskRepository.existsById(id)){
            return false;
        }

        taskRepository.deleteById(id);
        return true;
    }

    private Task transformEntity(TaskEntity taskEntity){
        return new Task(
                taskEntity.getId(),
                taskEntity.getTaskName()
        );
    }

}
