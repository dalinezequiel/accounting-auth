package com.api.security.repository;

import com.api.security.model.domain.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<UserModel, UUID> {
    UserModel findByUsername(String username);

    UserModel findByUsernameAndPassword(String username, String password);
}
