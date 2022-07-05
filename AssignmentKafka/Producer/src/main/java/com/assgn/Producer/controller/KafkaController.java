package com.assgn.Producer.controller;

import com.assgn.Producer.model.Employee;
import com.assgn.Producer.service.Producer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kafka")
public class KafkaController {
    @Autowired
    Producer producer;

    @PostMapping(value="/produce")
    public ResponseEntity<String> sendMessage(@RequestBody Employee emp){
        producer.publishToTopicString(emp);
        return new ResponseEntity<>("Successfully Sent", HttpStatus.OK);
    }
}
