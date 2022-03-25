package com.example.Security.repo;

import com.example.Security.Enities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Integer> {

    User findByEmail(String email);

}
