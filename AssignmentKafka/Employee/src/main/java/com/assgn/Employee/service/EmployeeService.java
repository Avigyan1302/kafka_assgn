package com.assgn.Employee.service;

import com.assgn.Employee.model.Employee;
import com.assgn.Employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @CachePut(value = "employeeCache", key = "#id")
    public Employee addEmp(int id, Employee emp){
        Employee employee = employeeRepository.save(emp);
        System.out.println("Saved");
        return employee;
    }

    @Cacheable(value = "employeeCache")
    public List<Employee> getAllEmp(){
        List<Employee> employeeList = employeeRepository.findAll();
        System.out.println("DB hit");
        return employeeList;
    }

    @Cacheable(value = "employeeCache", key = "#id")
    public Optional<Employee> getById(int id){
        Optional<Employee> employee = employeeRepository.findById(id);
        System.out.println("DB hit");
        return employee;
    }

    @CacheEvict(value = "employeeCache", key = "#id", allEntries = true)
    public void delete(int id){
        employeeRepository.deleteById(id);
        System.out.println("DB hit");
    }

    @CachePut(value = "employeeCache", key = "#id")
    public Employee update(int id, Employee emp){
        employeeRepository.save(emp);
        System.out.println("DB hit");
        return emp;
    }
}
