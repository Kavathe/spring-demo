package in.org.rebit.employee;


import in.org.rebit.employee.exception.EmployeeNotFoundException;

import in.org.rebit.employee.security.jwt.JwtUtil;
import in.org.rebit.employee.service.EmployeeService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class EmployeeServiceApplication {
    public static void main(String[] args){

        SpringApplication.run(EmployeeServiceApplication.class);

    }

}
