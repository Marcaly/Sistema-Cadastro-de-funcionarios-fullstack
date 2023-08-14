package com.marcal.crudbackend.domain.repositories;

import com.marcal.crudbackend.domain.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  boolean existsByEmailId(String emailId);
}
