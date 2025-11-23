package in.org.rebit.employee.security.service;


import in.org.rebit.employee.entity.Employee;
import in.org.rebit.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private EmployeeRepository repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("In loadUserByUsername");
        //fetch employee by email from DB
        System.out.println(username);
        Optional<in.org.rebit.employee.entity.Employee> o =  repo.findByEmail(username);
        //if employee found convert it to UserDetails object and return
        if(o.isPresent()){
            Employee foundEmployee = o.get();
            User authenticatedUser = new User(foundEmployee.getEmail()
                    ,foundEmployee.getPassword()
                    ,AuthorityUtils.createAuthorityList(foundEmployee.getRoles()));
            return authenticatedUser;
        }
        //if not found throw UsernameNotFoundException
        else {
            throw new UsernameNotFoundException(username);
        }
    }
}
