package com.exam.examserver.Service.Impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.examserver.Service.UserService;
import com.exam.examserver.models.User;
import com.exam.examserver.models.UserRole;
import com.exam.examserver.repository.RoleRepo;
import com.exam.examserver.repository.UserRepo;

@Service
public class UserImpl implements UserService{
    
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private RoleRepo roleRepo;

    @Override
    public User createUser(User user, Set<UserRole> userRoles) {
        User createdUser=null;
       try{ 
            User local=userRepo.findByUsername(user.getUsername());
            if(local!=null){
                System.out.println("User is already Present");
                throw new Exception("User is already Presnt");
            }else{
                System.out.print(user.getEmail());
                for(UserRole userRole :userRoles){
                   
                    this.roleRepo.save(userRole.getRole());
                    user.getUserRoles().add(userRole);
                }
                User savedUser=this.userRepo.save(user);
                createdUser=savedUser;
            }
       }catch(Exception exception){
          exception.printStackTrace();
       }
       return createdUser;
    }

    @Override
    public User getUser(String username) {
      User user=  this.userRepo.findByUsername(username);
      return user;
    }

    @Override
    public void deleteUser(Long userid) {
        try{
            this.userRepo.deleteById(userid);
        }catch(Exception e){
            e.printStackTrace();
        } 
    }
    
}
