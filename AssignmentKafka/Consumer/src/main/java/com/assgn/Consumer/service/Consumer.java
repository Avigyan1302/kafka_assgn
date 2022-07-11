package com.assgn.Consumer.service;

import com.assgn.Consumer.model.Employee;
import org.springframework.http.HttpEntity;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class Consumer {

    RestTemplate restTemplate = new RestTemplate();

    @KafkaListener(topics = "employee", groupId = "testgroup",
            containerFactory = "employeeKafkaListenerFactory")
    public void consumeFromTopic(Employee employee) {
        System.out.println("Consumed "+employee.toString()+" "+employee.getClass());
        HttpEntity<Employee> request = new HttpEntity<>(employee);
        Employee emp = restTemplate.postForObject("http://localhost:8083/employees/add",
                request,
                Employee.class);
        System.out.println("Save Complete");
    }
}
