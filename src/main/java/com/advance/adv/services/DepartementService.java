package com.advance.adv.services;

import java.util.List;

import com.advance.adv.models.Department;
import com.advance.adv.repos.DepartmentRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DepartementService {


    @Autowired
    private DepartmentRepo depRpo;
    
    public List<Department> listDep(){

         return depRpo.findAll();
    }
    
    public void saveOrUpdate(Department dep){
           depRpo.save(dep);
    }

    public void remove(int depId ){
          
        depRpo.deleteById(depId);
    }

    
    public Department findOne(int id){
        // This Not Recomanded 
      Department dep = new Department();
          dep = depRpo.findById(id);
      return dep;
                 
    }


}
