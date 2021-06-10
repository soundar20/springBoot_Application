package com.projectManagement.projectManagement.Repository;

import com.projectManagement.projectManagement.Model.TeamMembers;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamMembersRepository extends CrudRepository<TeamMembers,Integer>{
    
    @Override
    List<TeamMembers> findAll();
    
    TeamMembers findById(int joinId);
    
    List<TeamMembers> findByProjectId(int projectId);
    
    List<TeamMembers> findByMemberEmail(String memberEmail);
    
    List<TeamMembers> findByProjectName(String projectName);
    
    List<TeamMembers> findBymemberUserName(String memberUserName);

    TeamMembers save(TeamMembers teamMembers);

    @Query("SELECT tm FROM TeamMembers tm WHERE tm.memberEmail = :memberEmail AND tm.projectId = :projectId")
    public TeamMembers findByMemberEmailAndProjectId(@Param("memberEmail")String memberEmail,@Param("projectId")int projectId);
}
