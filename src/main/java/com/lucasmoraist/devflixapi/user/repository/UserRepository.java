package com.lucasmoraist.devflixapi.user.repository;

import com.lucasmoraist.devflixapi.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
