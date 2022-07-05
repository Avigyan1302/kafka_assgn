package com.assgn.Producer.service;

import com.assgn.Producer.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Producer {
    public static final String topic = "employee";

    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemp;


    public void publishToTopicString(Employee emp){
        System.out.println("Publish to topic "+ emp.id+" "+emp.name+" "+emp.age+" "+emp.dept+" "+emp.salary);
        this.kafkaTemp.send(topic, emp);
    }
}
