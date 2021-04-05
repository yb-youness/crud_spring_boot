package com.advance.adv.controllers;

import javax.validation.Valid;

import com.advance.adv.models.Department;
import com.advance.adv.services.DepartementService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
public class DepartmentController {
   @Autowired  
   private DepartementService depSer;




   //! This Methode To Bind The Form (Department)
   @GetMapping("/adddepartment")
   public String addDepForm(Model model){
       model.addAttribute("dep", new Department());
      model.addAttribute("action", "Add Department");
       return "adddep";
   }



//! This Methode Is To Add || to Upadate A department 
@PostMapping("/adddepartment")
public String addDepartment(@Valid @ModelAttribute("dep") Department dep,BindingResult result,RedirectAttributes attr,Model model ){
    
   String path ="redirect:/adddepartment";
  //! Validation 
              if(result.hasErrors() ){
                path="adddep";
                if(dep.getId()==null)
                 model.addAttribute("action", "Update Department");
                 else
                  model.addAttribute("action", "Add Department");

              }else{
    
     
     //! Bind Message Add || Update
      if(dep.getId()==null)
      attr.addFlashAttribute("msg","Department add Whith Sucesss !");
      else
       attr.addFlashAttribute("msg","Department Updated Whith Sucesss !");
     
      //! Save  Or Update 
     depSer.saveOrUpdate(dep);
     
              }

     return path;
}


//! This Methode Is To Delete A department
@GetMapping("/updatedep/{id}")
public String bindDepartment(@PathVariable("id") int id,Model  model){
  //Find The Department By Id
  Department dep = new Department();
            dep = depSer.findOne(id);
  
             model.addAttribute("dep", dep);
             model.addAttribute("action", "Update Department");

               return "adddep";

}



//! This Metode Is To  Delete The Department 
@GetMapping("/deletdep/{id}")
public String deleteDepartment(@PathVariable ("id") int id ,RedirectAttributes attr){
      attr.addFlashAttribute("msg","Department Deleted Whith Sucesss !");
    
      depSer.remove(id);
  return "redirect:/home";
}




}
