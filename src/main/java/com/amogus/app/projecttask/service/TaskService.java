package com.amogus.app.projecttask.service;

import com.amogus.app.projecttask.entity.Task;
import com.amogus.app.projecttask.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByProjectId(Long projectId) {
        return taskRepository.findByProjectId(projectId);
    }

    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Optional<Task> taskOptional = taskRepository.findById(id);
        if (taskOptional.isPresent()) {
            Task existingTask = taskOptional.get();
            existingTask.setName(updatedTask.getName());
            existingTask.setDescription(updatedTask.getDescription());
            existingTask.setStatus(updatedTask.getStatus());
            existingTask.setPriority(updatedTask.getPriority());
            existingTask.setEndDate(updatedTask.getEndDate());
            existingTask.setUpdatedDate(updatedTask.getUpdatedDate());
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("Task not found with ID: " + id);
        }
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
