package com.ivehicle.repository;

import com.ivehicle.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    public Optional<UserEntity> findByEmail(String email);

    public List<UserEntity> findUsersByEmail(String email);
}
