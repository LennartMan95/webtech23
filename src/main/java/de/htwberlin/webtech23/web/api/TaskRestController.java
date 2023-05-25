package de.htwberlin.webtech23.web.api;

import de.htwberlin.webtech23.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class TaskRestController {

    private final TaskService taskService;

    public TaskRestController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping(path = "/api/v1/tasks")
    public ResponseEntity<List<Task>> fetchTasks() {
        return ResponseEntity.ok(taskService.findAll());
    }

    @GetMapping(path = "/api/v1/tasks/{id}")
    public ResponseEntity<Task> fetchTaskById(@PathVariable Long id) {
        var task = taskService.findById(id);
        return task != null? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/api/v1/tasks")
    public ResponseEntity<Void> createTask(@RequestBody TaskManipulationRequest request) throws URISyntaxException {
        var task = taskService.create(request);
        URI uri = new URI("/api/v1/tasks/" + task.getId());
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(path = "/api/v1/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody TaskManipulationRequest request){
        var task = taskService.update(id, request);
        return  task  != null? ResponseEntity.ok(task) : ResponseEntity.notFound().build();
    }

    @DeleteMapping(path = "/api/v1/persons{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        boolean successful = taskService.deleteById(id);
        return successful? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

}
