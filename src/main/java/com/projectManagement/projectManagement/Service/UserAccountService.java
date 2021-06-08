package com.projectManagement.projectManagement.Service;

import com.projectManagement.projectManagement.Model.UserAccountModel;
import com.projectManagement.projectManagement.Repository.UserAccountRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserAccountService implements InterfaceUserAccount {
    
    @Autowired
    private UserAccountRepository userAccountRepository;
    
    @Override
    public List<UserAccountModel> findAll(){
        return userAccountRepository.findAll();
    }
    @Override
    public UserAccountModel findById(int id){
        return userAccountRepository.findById(id);
    }

    @Override
    public UserAccountModel findByUserEmail(String userEmail){
        return userAccountRepository.findByUserEmail(userEmail);
    }

    @Override
    public UserAccountModel findByUserEmailAndUserPassword(String userEmail,String userPassword){
        return userAccountRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
    }  
    
    @Override
    public UserAccountModel saveUserAccountModel(UserAccountModel userAccountModel){
        return userAccountRepository.save(userAccountModel);
    }

}
