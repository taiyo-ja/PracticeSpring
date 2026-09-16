package org.example.practicespring.repository;

import org.example.practicespring.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findByNameContainingAndDepartmentContaining(String name, String department);

}
