package com.tenten.hackathon.repository;
import com.tenten.hackathon.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, String> {
    public UserEntity findByUserEmail(String userEmail);
    public boolean existsByUserEmailAndUserPassword(String userEmail, String userPassword);

}
