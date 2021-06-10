package com.projectManagement.projectManagement.Model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class TeamMembers {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int joinId;
    String projectName;
    String memberUserName;
    String memberEmail;
    String joinDate;
    int projectId;

    public int getJoinId() {
        return joinId;
    }

    public void setJoinId(int joinId) {
        this.joinId = joinId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getMemberUserName() {
        return memberUserName;
    }

    public void setMemberUserName(String memberUserName) {
        this.memberUserName = memberUserName;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    

}
