package com.projectManagement.projectManagement.Repository;

import com.projectManagement.projectManagement.Model.UserAccountModel;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAccountRepository extends CrudRepository<UserAccountModel,Integer>{

     @Override
     public List<UserAccountModel> findAll();
    
    public UserAccountModel findById(int id);

    public UserAccountModel findByUserEmail(String userEmail);

    @Query("SELECT ua FROM UserAccountModel  ua WHERE ua.userEmail = :userEmail AND ua.userPassword = :userPassword")
    public UserAccountModel findByUserEmailAndUserPassword(@Param("userEmail")String userEmail,@Param("userPassword")String userPassword);
    
    public UserAccountModel save(UserAccountModel userAccountModel);

}
