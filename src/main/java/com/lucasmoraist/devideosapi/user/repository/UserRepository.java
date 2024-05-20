package com.lucasmoraist.devideosapi.user.repository;

import com.lucasmoraist.devideosapi.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
