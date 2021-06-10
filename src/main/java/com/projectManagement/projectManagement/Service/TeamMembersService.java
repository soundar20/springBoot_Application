package com.projectManagement.projectManagement.Service;

import com.projectManagement.projectManagement.Model.TeamMembers;
import com.projectManagement.projectManagement.Repository.TeamMembersRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TeamMembersService implements InterfaceTeamMembers{
    
    @Autowired
    TeamMembersRepository teamMembersRepository; 
    
    @Override
    public List<TeamMembers> findAll(){
        return teamMembersRepository.findAll();
    }
    
    @Override
    public TeamMembers findById(int joinId){
        return teamMembersRepository.findById(joinId);
    }
    
    @Override
    public List<TeamMembers> findByProjectId(int projectId){
        return teamMembersRepository.findByProjectId(projectId);
    }
    
    @Override
    public List<TeamMembers> findByMemberEmail(String memberEmail){
        return teamMembersRepository.findByMemberEmail(memberEmail);
    }
    
    @Override
    public List<TeamMembers> findByProjectName(String projectName){
        return teamMembersRepository.findByProjectName(projectName);
    }
    
    @Override
    public List<TeamMembers> findBymemberUserName(String memberUserName){
        return teamMembersRepository.findBymemberUserName(memberUserName);
    }

    @Override
    public TeamMembers save(TeamMembers teamMembers){
        return teamMembersRepository.save(teamMembers);
    }
    
    @Override
    public TeamMembers findByMemberEmailAndProjectId(String memberEmail,int projectId){
        return teamMembersRepository.findByMemberEmailAndProjectId(memberEmail, projectId);
    }
    
}
