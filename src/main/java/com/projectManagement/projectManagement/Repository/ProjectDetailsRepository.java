package com.projectManagement.projectManagement.Repository;

import com.projectManagement.projectManagement.Model.ProjectDetails;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectDetailsRepository extends CrudRepository<ProjectDetails,Integer> {
    
    public List<ProjectDetails> findAll();
    
    public ProjectDetails findByProjectId(int id);
    
    public List<ProjectDetails> findByProjectAdmin(String projectAdmin);
    
    public ProjectDetails findByProjectName(String projectName);
    
    public ProjectDetails save(ProjectDetails projectDetails);
   
   
}
