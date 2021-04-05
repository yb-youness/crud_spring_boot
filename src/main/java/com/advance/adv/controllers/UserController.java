package com.advance.adv.controllers;

import java.util.List;

import javax.validation.Valid;

import com.advance.adv.models.Department;
import com.advance.adv.models.User;
import com.advance.adv.services.DepartementService;
import com.advance.adv.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class UserController  {
    @Autowired
    private  UserService userSer;
    @Autowired
    private DepartementService depService;

    //! This Methode Must Display Info
 @GetMapping({"","/","home"})
   public String home(Model model){
       List<User> listUsers = userSer.listAll();
       List<Department> listDepartment = depService.listDep();
         model.addAttribute("listUsers", listUsers);
         model.addAttribute("listDepartments", listDepartment);
       return "index";
   }
   //! This Methode To Bind The Info To The Form (Emp) 
   @GetMapping({"/add","/addEmployee"})
   public String addEmpForm(Model model){
       //! Get The List Of The Department To Fill The Combo Box
        List<Department> departments = depService.listDep();
        model.addAttribute("listDep", departments);
        User user =new User();
             user.setDepartmentId(new Department(-1,null,null));
        model.addAttribute("user",user );
        model.addAttribute("action", "Add Employee"); //This For Dynamique Action
       return "addemp";

   }

   //! This Methode Is Responsable For Adding Or Updating (In Case If The Id Is Not Null )
   // @Valid To Enable Validation 
   // BidingResult Is Intrface To Invoke The Validation 
   // Add The Validation To The Tyamleaf 
   @PostMapping("/add")
   public String addEmp(@RequestParam String departments, @Valid User user ,BindingResult result,RedirectAttributes attr, Model model){
           String path = "redirect:/add";
        
                  //! Check For Validation 
                  int iddep =Integer.parseInt(departments);
                
                      if(result.hasErrors() || iddep==-1){
                             if(iddep ==-1){

                                 model.addAttribute("errorfiled","Add A Valid Department !!!");
                             }
                            // Add Dynamique Action 
                            if(user.getId()==null)
                              model.addAttribute("action","Add Employee");
                            else  
                              model.addAttribute("action","Update Employee");
                              
                             List<Department> listDepartment = depService.listDep();
                             model.addAttribute("listDep", listDepartment);
                            user.setDepartmentId(new Department(-1, null, null));

                           path="addemp";
                      }else{
                user.setDepartmentId(new Department(iddep, null, null));
                
                     // Add Flash Msg
                    if(user.getId()==null)
                    attr.addFlashAttribute("msg","User add Whith Sucesss !");
                    else
                    attr.addFlashAttribute("msg","User Updated Whith Sucesss !");

                    userSer.saveOrUpdate(user);
           
                      }
             
              return path;
   }
   
//! This Methode Is Responsable For Deleting Using The Param Path Variable 
    @GetMapping("/del/{id}")
    public String deleteEmploye(@PathVariable ("id") int id ,RedirectAttributes attr){
        userSer.deleteEmploye(id);
     attr.addFlashAttribute("msg","User Deleted Whith Sucesss !");

        return "redirect:/home";
    }


    @GetMapping("/update/{id}")
    public String updateEmploye(@PathVariable ("id") int id , Model model){
        // Find The Employe By Id 
          User emp = new User();
               emp = userSer.findOne(id);
        // Add The Id To The Object 
               emp.setId(id);
        // Bind The Model Must Match The Form Attribute + Add The th:value to The Tyemalef  
            model.addAttribute("user", emp);
        // Bind to  The The Form (Get Mapping ) Will Bind The Data To The Form 
         model.addAttribute("action", "Update Employee");
         
         //This To Get All Departments 
         List<Department> listDepartment = depService.listDep();
         model.addAttribute("listDep", listDepartment);
        return "addemp";
    }






   //! This Methode To  Show The About Page
   @GetMapping("/about")
   public String about(){
       return "about";
   }
}
