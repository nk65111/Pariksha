package com.exam.examserver.Service;

import java.util.Set;

import com.exam.examserver.models.User;
import com.exam.examserver.models.UserRole;

public interface UserService {
    public User createUser(User user,Set<UserRole> userRoles);

    public User getUser(String username);

    public void deleteUser(Long userid);
}
