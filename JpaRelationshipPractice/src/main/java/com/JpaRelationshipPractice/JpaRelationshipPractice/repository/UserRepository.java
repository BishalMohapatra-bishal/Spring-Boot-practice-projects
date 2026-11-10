package com.JpaRelationshipPractice.JpaRelationshipPractice.repository;

import com.JpaRelationshipPractice.JpaRelationshipPractice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
