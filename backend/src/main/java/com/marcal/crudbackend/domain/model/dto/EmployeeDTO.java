package com.marcal.crudbackend.domain.model.dto;

import com.marcal.crudbackend.domain.model.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@Setter
public class EmployeeDTO implements Serializable {

    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;

    public EmployeeDTO(Employee employee) {
        id = employee.getId();
        firstName = employee.getFirstName();
        lastName = employee.getLastName();
        emailId = employee.getEmailId();
    }


}
