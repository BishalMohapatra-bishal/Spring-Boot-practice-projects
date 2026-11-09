package com.JpaRelationshipPractice.JpaRelationshipPractice.repository;

import com.JpaRelationshipPractice.JpaRelationshipPractice.model.Records;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordsRepository extends JpaRepository<Records, Long> {
}
