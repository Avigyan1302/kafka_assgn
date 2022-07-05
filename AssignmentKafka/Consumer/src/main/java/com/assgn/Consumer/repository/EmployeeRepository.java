package com.assgn.Consumer.repository;

import com.assgn.Consumer.model.Employee;
import org.springframework.data.cassandra.repository.CassandraRepository;

public interface EmployeeRepository extends CassandraRepository<Employee, Integer> {
}
