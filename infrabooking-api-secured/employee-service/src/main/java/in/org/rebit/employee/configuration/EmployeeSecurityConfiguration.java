package in.org.rebit.employee.configuration;

import in.org.rebit.employee.security.jwt.JwtAutheticationFilter;
import in.org.rebit.employee.security.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableMethodSecurity
@Configuration
public class EmployeeSecurityConfiguration  {

    @Autowired
    private JwtUtil util;

    @Bean
    PasswordEncoder getEncoder(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }

    @Autowired
    private UserDetailsService detailsService;

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http,
                                             AuthenticationManager manager) throws Exception {


        //disable csrf
        http.csrf( c -> c.disable() );

        //instructing spring to fetch UserDetails from given object
        http.userDetailsService(detailsService);


        http.authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.POST,"/employee/login").permitAll() );

        http.authorizeHttpRequests( req -> req.requestMatchers(HttpMethod.GET,"/employee/**").hasRole("USER") );

        //instructing spring security to authenticate every request
        http.authorizeHttpRequests(req -> req.anyRequest()
                                                                        .authenticated());

        //use http basic authentication type
        //http.httpBasic(Customizer.withDefaults());

        //inserting custom filter in spring security filter chain
        http.addFilter(new JwtAutheticationFilter(manager,util));

        return http.build();
    }

    @Bean
    AuthenticationManager getManager(AuthenticationConfiguration c) throws Exception {
        return c.getAuthenticationManager();
    }


}
