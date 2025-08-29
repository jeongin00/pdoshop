package com.example.demo.repository.user;

import com.example.demo.repository.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLoginId(String loginId);
    //findById()는 JpaRepository<User, Integer> 가 기본 제공 메서드라서 가능함
}
