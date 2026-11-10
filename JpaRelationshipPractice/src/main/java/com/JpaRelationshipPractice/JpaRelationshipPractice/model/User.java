    package com.JpaRelationshipPractice.JpaRelationshipPractice.model;

    import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.Role;
    import com.JpaRelationshipPractice.JpaRelationshipPractice.enums.UserStatus;
    import jakarta.persistence.*;
    import lombok.*;

    import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;

    @Entity
    @Getter
    @Setter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String email;

        private String password;

        @Enumerated(EnumType.STRING)
        private Role role;

        @Enumerated(EnumType.STRING)
        private UserStatus status;

        private LocalDateTime createdAt;

        @OneToOne(
                mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true
        )
        private UserProfile profile;

        // --- HELPER METHOD FOR ONE-TO-ONE PROFILE ---
        public void assignProfile(UserProfile profile) {
            this.profile = profile;
            if (profile != null) {
                profile.setUser(this); // Crucially sets the owning side
            }
        }

        public void removeProfile() {
            if (this.profile != null) {
                this.profile.setUser(null); // Clears the owning side
                this.profile = null;
            }
        }

        @OneToMany(
                mappedBy = "user",
                cascade = CascadeType.ALL,
                orphanRemoval = true
        )
        private List<Records> bookRecords = new ArrayList<>();

        public void addRecord(Records record) {
            bookRecords.add(record);          // 1. Updates the collection on the non-owning side
            record.setUser(this);         // 2. Crucially sets the owning side (foreign key)
        }

        public void removeRecord(Records record) {
            bookRecords.remove(record);
            record.setUser(null);         // Clears the foreign key
        }
    }
