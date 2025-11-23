package in.org.rebit.employee.repository;


import in.org.rebit.employee.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmployeeRepository
        extends JpaRepository<Employee,Integer> {

    Optional<Employee> findByEmail(String email);
    void deleteByEmail(String email);
}
