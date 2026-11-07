package com.JpaRelationshipPractice.JpaRelationshipPractice.repository;

import com.JpaRelationshipPractice.JpaRelationshipPractice.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
