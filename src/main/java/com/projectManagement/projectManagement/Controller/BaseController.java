package com.projectManagement.projectManagement.Controller;

import com.projectManagement.projectManagement.Model.UserAccountModel;
import com.projectManagement.projectManagement.Service.InterfaceUserAccount;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BaseController {
    
    @Autowired
    InterfaceUserAccount interfaceUserAccount;
    
    @GetMapping("/")
    public ModelAndView index(){
       return new ModelAndView("home");
    }
    
    @PostMapping("/userLogin")
    public ModelAndView userLogin(@RequestParam("userEmail") String userEmail,@RequestParam("userPassword") String userPassword,HttpServletRequest request,HttpServletResponse response) throws IOException{
        System.out.println("UserEmail :" + userEmail);
        System.out.println("UserPassword : " +userPassword);
        UserAccountModel userAccountModel=interfaceUserAccount.findByUserEmailAndUserPassword(userEmail, userPassword);
        System.out.println("Db email and Db password: "+userAccountModel);
        if(userAccountModel!=null){
            HttpSession session=request.getSession();
            session.setAttribute("userEmail", userEmail);
            if(session.getAttribute("userEmail")==null){
                response.sendRedirect("home");
            }
            System.out.println("Session started id is : "+session.getId());
            ModelAndView mv=new ModelAndView("dashboard");
            return mv;
        }
        else{
            ModelAndView mv=new ModelAndView("home");
            mv.addObject("message", "ivalid user");
            mv.addObject("status", false);
            return mv;
        }
    }
    
    @GetMapping("/userregistration")
    public ModelAndView userRegistration(){
        System.out.println("Entered User Registration Controller");
        ModelAndView mv=new ModelAndView("userregistration");
        return mv;
    }
    
    @PostMapping("/completeUserRegistration")
    public ModelAndView completeUserRegistration(@RequestParam("userFirstName")String userFirstName,@RequestParam("userLastName")String userLastName,@RequestParam("userName")String userName,@RequestParam("userEmail")String userEmail,@RequestParam("userMobile")String userMobile,@RequestParam("userPassword")String userPassword,@RequestParam("ConfirmUserPassword")String ConfirmUserPassword){
            ModelAndView mv=new ModelAndView("home");
            System.out.println("UserFirstName : " +userFirstName);
            System.out.println("UserLastName : " +userLastName);
            System.out.println("UserName : " +userName);
            System.out.println("UserEmail : " +userEmail);
            System.out.println("UserPhoneno : " +userMobile);
            System.out.println("userpassword : "+userPassword);
            System.out.println("confirm password : "+ConfirmUserPassword);
            System.out.println("Password True or false :"+ConfirmUserPassword.equals(userPassword));
            if(ConfirmUserPassword.equals(userPassword)){
                UserAccountModel uAccountModel=interfaceUserAccount.findByUserEmail(userEmail);
                System.out.println("User Email from the DB : "+uAccountModel);
                if(uAccountModel==null){
                    UserAccountModel userAccount = new UserAccountModel();
                    userAccount.setUserFirstName(userFirstName);
                    userAccount.setUserLastName(userLastName);
                    userAccount.setUserName(userName);
                    userAccount.setUserEmail(userEmail);
                    userAccount.setUserMobile(userMobile);
                    userAccount.setUserPassword(userPassword);
                    interfaceUserAccount.saveUserAccountModel(userAccount);
                }
                else{
                    mv.addObject("message","Email is already in use");
                    mv.addObject("status",false);
                    mv.setViewName("userregistration");
                }
            }
            else{
                mv.setViewName("userregistration");
                mv.addObject("message", "incorrect password");
                mv.addObject("status", false);
            }
        return mv;
    }
    
    @RequestMapping("/logout")
    public ModelAndView logOut(HttpServletRequest request,HttpServletResponse response){
        ModelAndView mv=new ModelAndView("home");
        HttpSession session=request.getSession(false);
        session.invalidate();
        System.out.println("Logged out from the session");
        return mv;
    }
    
}
