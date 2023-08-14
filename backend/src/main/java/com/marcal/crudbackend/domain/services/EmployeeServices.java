package com.marcal.crudbackend.domain.services;

import com.marcal.crudbackend.domain.exception.DuplicateEmployeeException;
import com.marcal.crudbackend.domain.exception.ResourceNotFoundException;
import com.marcal.crudbackend.domain.model.Employee;
import com.marcal.crudbackend.domain.model.dto.EmployeeDTO;
import com.marcal.crudbackend.domain.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServices {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Long id) {
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id não encontrado"));
    }

    public Employee create(Employee employee) {
        if (employeeRepository.existsByEmailId(employee.getEmailId())) {
            throw new DuplicateEmployeeException("Um funcionário com o mesmo email já está registrado");
        }
        return employeeRepository.save(employee);
    }

    public void delete(Long id) {
        employeeRepository.deleteById(id);
    }

    public Employee update(Employee employee) {
        Employee newEmp = findById(employee.getId());
        updateData(newEmp, employee);
        if (employeeRepository.existsByEmailId(employee.getEmailId())) {
            throw new DuplicateEmployeeException("Um funcionário com o mesmo email já está registrado");
        }
        return employeeRepository.save(newEmp);
    }

    private void updateData(Employee newEmp, Employee employee) {
        newEmp.setFirstName(employee.getFirstName());
        newEmp.setLastName(employee.getLastName());
        newEmp.setEmailId(employee.getEmailId());
    }

    public Employee FromDTO(EmployeeDTO employeeDTO) {
        return new Employee(employeeDTO.getId(), employeeDTO.getFirstName(), employeeDTO.getLastName(),
                employeeDTO.getEmailId());
    }
}
