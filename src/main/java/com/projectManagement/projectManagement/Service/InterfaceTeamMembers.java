package com.projectManagement.projectManagement.Service;

import com.projectManagement.projectManagement.Model.TeamMembers;
import java.util.List;

public interface InterfaceTeamMembers {
    
    List<TeamMembers> findAll();
    
    TeamMembers findById(int joinId);
    
    List<TeamMembers> findByProjectId(int projectId);
    
    List<TeamMembers> findByMemberEmail(String memberEmail);
    
    List<TeamMembers> findByProjectName(String projectName);
    
    List<TeamMembers> findBymemberUserName(String memberUserName);

    TeamMembers save(TeamMembers teamMembers);
    
    TeamMembers findByMemberEmailAndProjectId(String memberEmail,int projectId);
}
