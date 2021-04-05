package com.advance.adv.services;

import java.util.List;

import com.advance.adv.models.User;
import com.advance.adv.repos.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;
    public void saveOrUpdate(User user){
        userRepo.save(user);
    }
    public List<User> listAll(){
        return userRepo.findAll();
    }

   
    public void deleteEmploye(int id){
        userRepo.deleteById(id);
    }

    public User findOne(int id){
        User user = new User();
            user = userRepo.findById(id);
        return user;
    }
}
