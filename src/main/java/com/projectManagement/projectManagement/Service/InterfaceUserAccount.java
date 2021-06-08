package com.projectManagement.projectManagement.Service;

import com.projectManagement.projectManagement.Model.UserAccountModel;
import java.util.List;

public interface InterfaceUserAccount {
    
    public List<UserAccountModel> findAll();

    public UserAccountModel findById(int id);
    
    public UserAccountModel findByUserEmail(String userEmail);
    
    public UserAccountModel findByUserEmailAndUserPassword(String userEmail,String userPassword);
    
    public UserAccountModel saveUserAccountModel(UserAccountModel userAccountModel);
}
