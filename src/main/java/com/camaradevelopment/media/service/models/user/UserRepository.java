package com.camaradevelopment.media.service.models.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    User findUserById (int id);

    List<User> findUsersByFirstNameContainingIgnoreCase(String fistName);

    List<User> findUsersByLastNameContainingIgnoreCase(String lastName);

}
