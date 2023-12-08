package com.nutritiontracker.nutritiontrackerbackend.user.service;

import com.nutritiontracker.nutritiontrackerbackend.user.exception.UserNotFoundException;
import com.nutritiontracker.nutritiontrackerbackend.user.model.User;
import com.nutritiontracker.nutritiontrackerbackend.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository up;

    public User createNewAccount(User user) {
        return up.save(user);
    }

    public User getAccount(Long id) {
        return up.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }
}
