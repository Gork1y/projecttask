package com.amogus.app.projecttask.service;

import com.amogus.app.projecttask.entity.Project;
import com.amogus.app.projecttask.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project createProject(Project project) {
        return projectRepository.save(project);
    }

    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    public Optional<Project> getProjectById(Long id) {
        return projectRepository.findById(id);
    }

    public Project updateProject(Long id, Project updatedProject) {
        Optional<Project> projectOptional = projectRepository.findById(id);
        if (projectOptional.isPresent()) {
            Project existingProject = projectOptional.get();
            existingProject.setName(updatedProject.getName());
            existingProject.setDescription(updatedProject.getDescription());
            existingProject.setStartDate(updatedProject.getStartDate());
            existingProject.setEndDate(updatedProject.getEndDate());
            existingProject.setUpdatedDate(updatedProject.getUpdatedDate());
            return projectRepository.save(existingProject);
        } else {
            throw new RuntimeException("Project not found with ID: " + id);
        }
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }
}
