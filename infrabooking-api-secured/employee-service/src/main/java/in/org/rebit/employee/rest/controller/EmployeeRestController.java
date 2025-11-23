package in.org.rebit.employee.rest.controller;


import in.org.rebit.employee.entity.Employee;
import in.org.rebit.employee.exception.EmployeeNotFoundException;
import in.org.rebit.employee.security.jwt.JwtUtil;
import in.org.rebit.employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeRestController {

    @Autowired
    private EmployeeService service;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private JwtUtil util;
    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Employee e){
        Map<String,String> response = new HashMap<>();
        Authentication auth = new UsernamePasswordAuthenticationToken(e.getEmail(),
                                                        e.getPassword());
        Authentication authentication = manager.authenticate(auth);
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();
        System.out.println(roles);
        String token = util.createToken(e.getEmail(),roles);
        response.put("jwtToken",token);
        return response;

    }

    @PostAuthorize("returnObject.email == authentication.name")
    @GetMapping("/{email}")
    public Employee searchByEmail(@PathVariable String email) throws EmployeeNotFoundException {
        return this.service.findByEmail(email);
    }

    @PostMapping
    public Employee register(@RequestBody in.org.rebit.employee.entity.Employee e ){
        return this.service.registerEmployee(e);
    }

}
