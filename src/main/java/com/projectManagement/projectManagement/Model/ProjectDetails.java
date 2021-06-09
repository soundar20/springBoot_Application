package com.projectManagement.projectManagement.Model;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class ProjectDetails {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int projectId;
    String projectName;
    String projectDescription;
    String projectTechnology;
    int projectCapacity;
    String projectAdmin;
    String creationDate;

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getProjectTechnology() {
        return projectTechnology;
    }

    public void setProjectTechnology(String projectTechnology) {
        this.projectTechnology = projectTechnology;
    }

    public int getProjectCapacity() {
        return projectCapacity;
    }

    public void setProjectCapacity(int projectCapacity) {
        this.projectCapacity = projectCapacity;
    }

    public String getProjectAdmin() {
        return projectAdmin;
    }

    public void setProjectAdmin(String projectAdmin) {
        this.projectAdmin = projectAdmin;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    
}
