package com.marcal.crudbackend.domain.controller;

import com.marcal.crudbackend.domain.model.Employee;
import com.marcal.crudbackend.domain.model.dto.EmployeeDTO;
import com.marcal.crudbackend.services.EmployeeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EmployeeController {

    @Autowired
    private EmployeeServices employeeServices;

    // get all employees
    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<Employee> list = employeeServices.findAll();
        List<EmployeeDTO> listDTO = list.stream().map(x -> new EmployeeDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);

    }

    // get employee by id rest api
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        Employee emp = employeeServices.findById(id);
        return ResponseEntity.ok().body(new EmployeeDTO(emp));
    }

    // create employee rest api
    @PostMapping("/employees")
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeServices.FromDTO(employeeDTO);
        employeeServices.create(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }


    // update employee rest api

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        Employee employee = employeeServices.FromDTO(employeeDTO);
        employee.setId(id);
        employeeServices.update(employee);
        return ResponseEntity.noContent().build();
    }

    // delete employee rest api
    @DeleteMapping("/employees/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        employeeServices.delete(id);
        return ResponseEntity.noContent().build();


    }

}