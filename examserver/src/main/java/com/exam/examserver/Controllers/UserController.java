package com.exam.examserver.Controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.examserver.Service.UserService;
import com.exam.examserver.models.Role;
import com.exam.examserver.models.User;
import com.exam.examserver.models.UserRole;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PostMapping("/")
    public ResponseEntity<User> createUser(@RequestBody User user){
        Role role=new Role();
		role.setRoleName("NORMAL");
		UserRole userRole=new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
        Set<UserRole> set=new HashSet<>();
		set.add(userRole);
        User saveduser= this.userService.createUser(user, set);
        return new ResponseEntity<>(saveduser,HttpStatus.CREATED);
    }
    
    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable("username") String username){
        User user= this.userService.getUser(username);
        return new ResponseEntity<>(user,HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{userid}")
    public void deleteUser(@PathVariable("userid") Long userid){
         this.userService.deleteUser(userid);
    }
}
