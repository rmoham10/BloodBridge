package org.example.bloodbridge.repository;

import org.example.bloodbridge.entity.BloodGroup;
import org.example.bloodbridge.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByBloodGroup(BloodGroup bloodGroup);
}
