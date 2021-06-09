package com.projectManagement.projectManagement.Service;

import com.projectManagement.projectManagement.Model.ProjectDetails;
import java.util.List;

public interface InterfaceProjectDetails {
    
    List<ProjectDetails> findAll();
    
    ProjectDetails findByProjectId(int id);
    
    List<ProjectDetails> findByProjectAdmin(String projectAdmin);
    
    ProjectDetails findByProjectName(String projectName);
    
    ProjectDetails save(ProjectDetails projectDetails);
}
