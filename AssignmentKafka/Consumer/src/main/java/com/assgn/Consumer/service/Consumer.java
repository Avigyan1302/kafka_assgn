package com.assgn.Consumer.service;

import com.assgn.Consumer.model.Employee;
import com.assgn.Consumer.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Consumer {

    @Autowired
    EmployeeRepository employeeRepository;

    //Cache store after post - new call
    //Cache store after post - cache (No records)

    @KafkaListener(topics = "employee", groupId = "testgroup",
            containerFactory = "employeeKafkaListenerFactory")
    //@CacheEvict(value = "employeeCache", key = "#id", condition = "#id!=null")
    public void consumeFromTopic(Employee employee) {
        System.out.println("Consumed "+employee.toString()+" "+employee.getClass());
        addEmp(employee);
    }

    //@Cacheable(value = "employeeCache", key = "#id")
    public Employee addEmp(Employee emp){
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
