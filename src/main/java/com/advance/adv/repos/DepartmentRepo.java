package com.advance.adv.repos;

import java.util.List;


import com.advance.adv.models.Department;

import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepo extends CrudRepository<Department,Integer> {
    
    @Override
    public List<Department> findAll();

    public Department findById(int id);
       



}
