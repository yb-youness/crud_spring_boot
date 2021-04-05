package com.advance.adv.repos;

import java.util.List;

import com.advance.adv.models.User;


import org.springframework.data.repository.CrudRepository;

public interface UserRepo  extends CrudRepository<User,Integer>{
    @Override
    public List<User> findAll();

    public User save(User user);

    public User findById(int id);

  

    
    
}
