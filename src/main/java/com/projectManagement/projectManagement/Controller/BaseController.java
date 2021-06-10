package com.projectManagement.projectManagement.Controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.projectManagement.projectManagement.Model.ProjectDetails;
import com.projectManagement.projectManagement.Model.TeamMembers;
import com.projectManagement.projectManagement.Model.UserAccountModel;
import com.projectManagement.projectManagement.Service.InterfaceProjectDetails;
import com.projectManagement.projectManagement.Service.InterfaceTeamMembers;
import com.projectManagement.projectManagement.Service.InterfaceUserAccount;

@Controller
public class BaseController {

    @Autowired
    InterfaceUserAccount interfaceUserAccount;

    @Autowired
    InterfaceProjectDetails interfaceProjectDetails;

    @Autowired
    InterfaceTeamMembers interfaceTeamMembers;

    @GetMapping("/")
    public ModelAndView index() {
        System.out.println("Get home");
        return new ModelAndView("home");
    }

    @PostMapping("/userLogin")
    public ModelAndView userLogin(@RequestParam("userEmail") String userEmail,
            @RequestParam("userPassword") String userPassword, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        System.out.println("post user Login page");
        System.out.println("UserEmail :" + userEmail);
        System.out.println("UserPassword : " + userPassword);
        UserAccountModel userAccountModel = interfaceUserAccount.findByUserEmailAndUserPassword(userEmail,
                userPassword);
        System.out.println("Db email and Db password: " + userAccountModel);
        if (userAccountModel != null) {
            HttpSession session = request.getSession();
            session.setAttribute("userEmail", userEmail);
            if (session.getAttribute("userEmail") == null) {
                response.sendRedirect("home");
            }
            System.out.println("Session started id is : " + session.getId());
            return new ModelAndView("redirect:/createProject");
        } else {
            ModelAndView mv = new ModelAndView("home");
            mv.addObject("message", "ivalid user");
            mv.addObject("status", false);
            return mv;
        }
    }

    @GetMapping("/userregistration")
    public ModelAndView userRegistration() {
        System.out.println("Get user Registrartion");
        System.out.println("Entered User Registration Controller");
        ModelAndView mv = new ModelAndView("userregistration");
        return mv;
    }

    @PostMapping("/completeUserRegistration")
    public ModelAndView completeUserRegistration(@RequestParam("userFirstName") String userFirstName,
            @RequestParam("userLastName") String userLastName, @RequestParam("userName") String userName,
            @RequestParam("userEmail") String userEmail, @RequestParam("userMobile") String userMobile,
            @RequestParam("userPassword") String userPassword,
            @RequestParam("ConfirmUserPassword") String ConfirmUserPassword) {
        System.out.println("post complete User Registration");
        ModelAndView mv = new ModelAndView("home");
        System.out.println("UserFirstName : " + userFirstName);
        System.out.println("UserLastName : " + userLastName);
        System.out.println("UserName : " + userName);
        System.out.println("UserEmail : " + userEmail);
        System.out.println("UserPhoneno : " + userMobile);
        System.out.println("userpassword : " + userPassword);
        System.out.println("confirm password : " + ConfirmUserPassword);
        System.out.println("Password True or false :" + ConfirmUserPassword.equals(userPassword));
        if (ConfirmUserPassword.equals(userPassword)) {
            UserAccountModel uAccountModel = interfaceUserAccount.findByUserEmail(userEmail);
            System.out.println("User Email from the DB : " + uAccountModel);
            if (uAccountModel == null) {
                UserAccountModel userAccount = new UserAccountModel();
                userAccount.setUserFirstName(userFirstName);
                userAccount.setUserLastName(userLastName);
                userAccount.setUserName(userName);
                userAccount.setUserEmail(userEmail);
                userAccount.setUserMobile(userMobile);
                userAccount.setUserPassword(userPassword);
                interfaceUserAccount.saveUserAccountModel(userAccount);
            } else {
                mv.addObject("message", "Email is already in use");
                mv.addObject("status", false);
                mv.setViewName("userregistration");
            }
        } else {
            mv.setViewName("userregistration");
            mv.addObject("message", "incorrect password");
            mv.addObject("status", false);
        }
        return mv;
    }

    @GetMapping("/logout")
    public ModelAndView logOut(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("post logout");
        ModelAndView mv = new ModelAndView("home");
        HttpSession session = request.getSession(false);
        session.invalidate();
        System.out.println("Logged out from the session");
        return mv;
    }

    @GetMapping("/createProject")
    public ModelAndView createProject(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("get create Project");
        ModelAndView mv = new ModelAndView("dashboard");
        List<ProjectDetails> getProjectDetails = interfaceProjectDetails.findAll();
        System.out.println("Project from DB : " + getProjectDetails);
        // get the user email from session valid the user email from the user DB if the
        // user is available send the user date to the ui as a object
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");
        System.out.println("Session value: " + email);
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean emailVaildator = matcher.matches();
        System.out.println("Email validation: " + emailVaildator);
        if (emailVaildator == true) {
            System.out.println("Email is valid");
            UserAccountModel uAccountModel = interfaceUserAccount.findByUserEmail(email);
            System.out.println("Get User By Email : " + uAccountModel);
            if (uAccountModel != null) {
                mv.addObject("userData", uAccountModel);
            } else {
                mv.addObject("message", "user not found");
                mv.addObject("status", false);
            }
        } else {
            mv.addObject("message", "session user not found");
            mv.addObject("status", false);
        }
        System.out.println("projectDetailsList: " + getProjectDetails.isEmpty());
        System.out.println("projectDetailsList: " + getProjectDetails.size());
        mv.addObject("projectDetailsList", getProjectDetails);
        return mv;
    }

    @PostMapping("/completeproject")
    public ModelAndView completeProject(@RequestParam("projectName") String projectName,
            @RequestParam("projectTechnology") String projectTechnology,
            @RequestParam("projectDescription") String projectDescription, HttpServletRequest request,
            HttpServletResponse response) {
        System.out.println("post complete Project");
        System.out.println("Project Name is : " + projectName);
        System.out.println("Project Technology is : " + projectTechnology);
        System.out.println("Project Description is : " + projectDescription);
        ModelAndView mv = new ModelAndView("dashboard");
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");
        System.out.println("Session value: " + email);
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean emailVaildator = matcher.matches();
        System.out.println("Email validation: " + emailVaildator);
        if (emailVaildator == true) {
            System.out.println("Email is valid");
            // check in the db is user is available or not to get the user data
            UserAccountModel uAccountModel = interfaceUserAccount.findByUserEmail(email);
            System.out.println("Get User By Email : " + uAccountModel);
            if (uAccountModel != null) {
                mv.addObject("userData", uAccountModel);
                ProjectDetails projectDetails = interfaceProjectDetails.findByProjectName(projectName);
                System.out.println("Check project avaliable in DB: " + projectDetails);
                if (projectDetails == null) {
                    ProjectDetails pd = new ProjectDetails();
                    pd.setProjectName(projectName);
                    pd.setProjectTechnology(projectTechnology);
                    pd.setProjectDescription(projectDescription);
                    pd.setProjectAdmin(email);
                    int teamCapacity = 1;
                    pd.setProjectCapacity(teamCapacity);
                    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                    LocalDateTime now = LocalDateTime.now();
                    System.out.println(dtf.format(now));
                    pd.setCreationDate(dtf.format(now));
                    interfaceProjectDetails.save(pd);
                    System.out.println("Details Saved");
                    mv.addObject("message", "project created");
                    mv.addObject("status", true);
                } else {
                    mv.addObject("message", "project name exists,try again");
                    mv.addObject("status", false);
                }
            } else {
                mv.addObject("message", "user not found");
                mv.addObject("status", false);
            }
        } else {
            mv.addObject("message", "session user not found");
            mv.addObject("status", false);
        }
        List<ProjectDetails> getProjectDetails = interfaceProjectDetails.findAll();
        System.out.println("Project from DB : " + getProjectDetails);
        mv.addObject("projectDetailsList", getProjectDetails);
        return mv;
    }

    @PostMapping("/joinProject")
    public ModelAndView joinProject(@RequestParam("userEmail") String userEmail,
            @RequestParam("projectId") int projectId, HttpServletRequest request, HttpServletResponse response) {
        System.out.println("PostMapping JoinProject");
        System.out.println("User Email : " + userEmail);
        System.out.println("Project id : " + projectId);
        ModelAndView mv = new ModelAndView("dashboard");
        List<ProjectDetails> getProjectDetails = interfaceProjectDetails.findAll();
        System.out.println("Project from DB : " + getProjectDetails);
        // HttpSession session=request.getSession();
        // String email=(String) session.getAttribute("userEmail");
        // System.out.println("Session value: "+email);
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(userEmail);
        boolean emailVaildator = matcher.matches();
        System.out.println("Email validation: " + emailVaildator);
        if (emailVaildator == true) {
            System.out.println("Email is valid");
            UserAccountModel uAccountModel = interfaceUserAccount.findByUserEmail(userEmail);
            System.out.println("Get User By Email : " + uAccountModel);
            if (uAccountModel != null) {
                ProjectDetails projectDetails = interfaceProjectDetails.findByProjectId(projectId);
                System.out.println("project details : " + projectDetails);
                System.out.println("projectDetails : " + projectDetails.getProjectCapacity());
                if (!projectDetails.getProjectAdmin().equals(userEmail)) {
                    // if(userEmail!=proj...)
                    System.out
                            .println("chceking validation: " + userEmail + " and " + projectDetails.getProjectAdmin());
                    System.out.println("user not admin");
                    // validate the useremail and projectid in the team members if its null the user
                    // allow to join the project
                    TeamMembers get = interfaceTeamMembers.findByMemberEmailAndProjectId(uAccountModel.getUserEmail(),
                            projectDetails.getProjectId());
                    System.out.println("email and project id : " + get);
                    if (get == null) {
                        if (projectDetails.getProjectCapacity() < 3) {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
                            LocalDateTime now = LocalDateTime.now();
                            System.out.println(dtf.format(now));
                            TeamMembers teamMembers = new TeamMembers();
                            teamMembers.setProjectName(projectDetails.getProjectName());
                            teamMembers.setMemberUserName(uAccountModel.getUserName());
                            teamMembers.setMemberEmail(uAccountModel.getUserEmail());
                            teamMembers.setJoinDate(dtf.format(now));
                            teamMembers.setProjectId(projectDetails.getProjectId());
                            interfaceTeamMembers.save(teamMembers);
                            System.out.println("getProjectCapacity() :" + projectDetails.getProjectCapacity());
                            projectDetails.setProjectCapacity(projectDetails.getProjectCapacity() + 1);
                            interfaceProjectDetails.save(projectDetails);
                            mv.addObject("joinMessage", "user joined");
                            mv.addObject("joinStatus", true);
                            System.out.println("user added");

                        } else {
                            System.out.println("max capacity is 5");
                            mv.addObject("joinMessage", "maximum capacity is 5");
                            mv.addObject("joinStatus", false);
                        }
                    } else {
                        mv.addObject("joinMessage", "The user is already exists");
                        mv.addObject("joinStatus", false);
                    }
                } else {
                    System.out.println("project admin already exists");

                    mv.addObject("joinMessage", "project admin already exists");
                    mv.addObject("joinStatus", false);
                }
                mv.addObject("userData", uAccountModel);
            } else {
                System.out.println("user not found");

                mv.addObject("joinMessage", "user not found");
                mv.addObject("joinStatus", false);
            }
        } else {
            System.out.println("user not found");
            mv.addObject("joinMessage", "session user not found");
            mv.addObject("joinStatus", false);
        }
        mv.addObject("projectDetailsList", getProjectDetails);
        return mv;
    }
    
    @GetMapping("/viewAllProject")
    public ModelAndView viewAllProject(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("get view all Project");
        ModelAndView mv = new ModelAndView("viewproject");
        List<ProjectDetails> getProjectDetails = interfaceProjectDetails.findAll();
        System.out.println("Project from DB : " + getProjectDetails);
        // get the user email from session valid the user email from the user DB if the
        // user is available send the user date to the ui as a object
        HttpSession session = request.getSession();
        String email = (String) session.getAttribute("userEmail");
        System.out.println("Session value: " + email);
        String regex = "^(.+)@(.+)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        boolean emailVaildator = matcher.matches();
        System.out.println("Email validation: " + emailVaildator);
        if (emailVaildator == true) {
            System.out.println("Email is valid");
            UserAccountModel uAccountModel = interfaceUserAccount.findByUserEmail(email);
            System.out.println("Get User By Email : " + uAccountModel);
            if (uAccountModel != null) {
                mv.addObject("userData", uAccountModel);
            } else {
                mv.addObject("message", "user not found");
                mv.addObject("status", false);
            }
        } else {
            mv.addObject("message", "session user not found");
            mv.addObject("status", false);
        }
        System.out.println("projectDetailsList: " + getProjectDetails.isEmpty());
        System.out.println("projectDetailsList: " + getProjectDetails.size());
        mv.addObject("projectDetailsList", getProjectDetails);
        return mv;
    }



}
