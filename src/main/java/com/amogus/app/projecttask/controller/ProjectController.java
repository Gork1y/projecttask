package com.amogus.app.projecttask.controller;

import com.amogus.app.projecttask.api.ProjectApi;
import com.amogus.app.projecttask.entity.Project;
import com.amogus.app.projecttask.service.ProjectService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProjectController implements ProjectApi {

    private final ProjectService projectService;

    @Override
    public ResponseEntity<Project> createProject(Project project) {
        return ResponseEntity.ok(projectService.createProject(project));
    }

    @Override
    public ResponseEntity<List<Project>> getAllProjects() {
        return ResponseEntity.ok(projectService.getAllProjects());
    }

    @Override
    public ResponseEntity<Project> getProjectById(Long id) {
        return projectService.getProjectById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<Project> updateProject(Long id, Project project) {
        return ResponseEntity.ok(projectService.updateProject(id, project));
    }

    @Override
    public ResponseEntity<Void> deleteProject(Long id) {
        projectService.deleteProject(id);
        return ResponseEntity.noContent().build();
    }
}
