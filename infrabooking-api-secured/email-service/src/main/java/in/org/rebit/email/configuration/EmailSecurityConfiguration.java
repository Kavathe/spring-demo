package in.org.rebit.email.configuration;
import in.org.rebit.email.jwt.JwtAutheticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class EmailSecurityConfiguration {

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http, AuthenticationManager manager) throws Exception {
        http.csrf(c -> c.disable());
        http.
                authorizeHttpRequests(req
                        -> req.requestMatchers(HttpMethod.GET
                        ,"/email").hasRole("USER"));
        http.
                authorizeHttpRequests(
                        request ->
                                request.anyRequest().authenticated());

        http.addFilter(new JwtAutheticationFilter(manager));

        return http.build();
    }

    @Bean
    AuthenticationManager getManager(AuthenticationConfiguration a) throws Exception {
        return a.getAuthenticationManager();
    }


}
