package com.amogus.app.projecttask.api;

import com.amogus.app.projecttask.entity.Task;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/tasks")
public interface TaskApi {

    @Operation(summary = "Создание новой задачи", description = "Создает новую задачу на основе переданных данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно создана", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "400", description = "Некорректные данные", content = @Content)
    })
    @PostMapping
    ResponseEntity<Task> createTask(@RequestBody Task task);

    @Operation(summary = "Получение списка всех задач", description = "Возвращает список всех задач")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач получен", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Задачи не найдены", content = @Content)
    })
    @GetMapping
    ResponseEntity<List<Task>> getAllTasks();

    @Operation(summary = "Получение списка задач по проекту", description = "Возвращает задачи по ID проекта")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список задач по проекту получен", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Задачи не найдены", content = @Content)
    })
    @GetMapping("/project/{projectId}")
    ResponseEntity<List<Task>> getTasksByProjectId(@PathVariable Long projectId);

    @Operation(summary = "Получение задачи по ID", description = "Возвращает задачу по её ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача найдена", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content)
    })
    @GetMapping("/{id}")
    ResponseEntity<Task> getTaskById(@PathVariable Long id);

    @Operation(summary = "Обновление задачи", description = "Обновляет существующую задачу по её ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача успешно обновлена", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Task.class))),
            @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content)
    })
    @PutMapping("/{id}")
    ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task task);

    @Operation(summary = "Удаление задачи", description = "Удаляет задачу по её ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Задача успешно удалена"),
            @ApiResponse(responseCode = "404", description = "Задача не найдена", content = @Content)
    })
    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable Long id);
}

