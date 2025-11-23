package in.org.rebit.employee.service;


import in.org.rebit.employee.entity.Employee;
import in.org.rebit.employee.exception.EmployeeNotFoundException;

public interface EmployeeService {

    Employee findByEmail(String email)throws EmployeeNotFoundException  ;
    Employee registerEmployee(Employee e);
    void deleteByEmail(String email);
}
