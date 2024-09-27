package com.amogus.app.projecttask.controller;

import com.amogus.app.projecttask.api.TaskApi;
import com.amogus.app.projecttask.entity.Task;
import com.amogus.app.projecttask.service.TaskService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TaskController implements TaskApi {

    private final TaskService taskService;

    @Override
    public ResponseEntity<Task> createTask(Task task) {
        return ResponseEntity.ok(taskService.createTask(task));
    }

    @Override
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @Override
    public ResponseEntity<List<Task>> getTasksByProjectId(Long projectId) {
        return ResponseEntity.ok(taskService.getTasksByProjectId(projectId));
    }

    @Override
    public ResponseEntity<Task> getTaskById(Long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Task> updateTask(Long id, Task task) {
        return ResponseEntity.ok(taskService.updateTask(id, task));
    }

    @Override
    public ResponseEntity<Void> deleteTask(Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
