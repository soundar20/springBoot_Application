package com.projectManagement.projectManagement.Service;

import com.projectManagement.projectManagement.Model.ProjectDetails;
import com.projectManagement.projectManagement.Repository.ProjectDetailsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectDetailsService implements InterfaceProjectDetails{
    
    @Autowired
    ProjectDetailsRepository projectDetailsRepository;

    @Override
    public List<ProjectDetails> findAll() {
        return projectDetailsRepository.findAll();
    }

    @Override
    public ProjectDetails findByProjectId(int id) {
        return projectDetailsRepository.findByProjectId(id);
    }

    @Override
    public List<ProjectDetails> findByProjectAdmin(String projectAdmin) {
        return projectDetailsRepository.findByProjectAdmin(projectAdmin);
    }

    @Override
    public ProjectDetails findByProjectName(String projectName) {
        return projectDetailsRepository.findByProjectName(projectName);
    }

    @Override
    public ProjectDetails save(ProjectDetails projectDetails) {
        return projectDetailsRepository.save(projectDetails);
    }
    
}
