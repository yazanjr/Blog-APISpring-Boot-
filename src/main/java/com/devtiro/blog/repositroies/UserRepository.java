package com.devtiro.blog.repositroies;

import com.devtiro.blog.domain.enities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);// by using this name spring jpa well know what to do
}
