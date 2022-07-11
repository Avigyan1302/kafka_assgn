package com.assgn.Employee.controller;

import com.assgn.Employee.model.Employee;
import com.assgn.Employee.repository.EmployeeRepository;
import com.assgn.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    EmployeeService employeeService;

    @PostMapping("/add")
    public ResponseEntity addEmp(@RequestBody Employee emp){
        Employee employee = employeeService.addEmp(emp.getId(),emp);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateEmp(@PathVariable("id") int id, @RequestBody Employee emp){
        Optional<Employee> emp1 = employeeService.getById(id);
        if(emp1.isPresent()==false)
            return new ResponseEntity<>("No record found", HttpStatus.OK);
        Employee employee = employeeService.update(id, emp);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getEmployees(){
        List<Employee> employees = employeeService.getAllEmp();
        return employees.size()!=0? new ResponseEntity<>(employees, HttpStatus.OK)
                :new ResponseEntity<>("No records", HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeById(@PathVariable("id") int id){
        Optional<Employee> employee = employeeService.getById(id);
        return employee.isPresent()? new ResponseEntity<>(employee,HttpStatus.OK)
                :new ResponseEntity<>("No record found",HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id){
        employeeService.delete(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }
}
