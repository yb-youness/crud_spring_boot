package com.advance.adv.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.validation.constraints.Email;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;








//! To Use Add In The Pom.xml 
    // The Validation Api 

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message="Name Is Required !!!")
    private String name;
    @NotBlank(message="Email Is Required !!!")
    @Email(message = "Email Format  Not Valid !!!")
    private String email;
   @NotNull(message = "Salarie Is Required !!!")
    private Double sal;

    //! To Add Forgin key you Must create A List Of This Object Int The Tables  and Add The OneToMany @anotation 
    @ManyToOne(cascade = CascadeType.DETACH,fetch = FetchType.LAZY)
    @JoinColumn(name="departmentId")
     private Department departmentId; 

   public User(){}

   public User(String name, String email, Double sal, Department depid) {
        this.name = name;
        this.email = email;
        this.sal = sal;
        this.departmentId = depid;
    }

       //! ReFactor Using Project Lombok
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
   
    public Double getSal() {
        return sal;
    }

    public void setSal(Double sal) {
        this.sal = sal;
    }

    

    public int getDepid() {
        return depid;
    }

    public void setDepid(int depid) {
        this.depid = depid;
    }

    @Override
    public String toString() {
        return "User [depid=" + depid + ", email=" + email + ", id=" + id + ", name=" + name + ", sal=" + sal + "]";
    }

 

    

    
       public Department getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Department departmentId) {
        this.departmentId = departmentId;
    }

    
    
}
