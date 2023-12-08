package com.nutritiontracker.nutritiontrackerbackend.user.repository;

import com.nutritiontracker.nutritiontrackerbackend.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
