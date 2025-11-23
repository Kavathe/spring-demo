package in.org.rebit.employee.service.impl;


import in.org.rebit.employee.entity.Employee;
import in.org.rebit.employee.exception.EmployeeNotFoundException;
import in.org.rebit.employee.repository.EmployeeRepository;
import in.org.rebit.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private EmployeeRepository repo;

    @Override
    public Employee findByEmail(String email) throws EmployeeNotFoundException {
        Optional<Employee> o = this.repo.findByEmail(email);
        return o.orElseThrow(() -> new EmployeeNotFoundException());
    }
    @Override
    public Employee registerEmployee(Employee e) {
        //encode password
        e.setPassword(encoder.encode(e.getPassword()));
        //assigning default role
        e.setRoles(List.of("ROLE_USER"));
        Employee savedEmployee =  this.repo.save(e);
        //not sending password in response
        savedEmployee.setPassword("");
        return savedEmployee;
    }

    @Override
    public void deleteByEmail(String email) {
        this.repo.deleteByEmail(email);
    }

}
