package in.org.rebit.email.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.json.JsonParser;
import org.springframework.boot.json.JsonParserFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

//responsible for
//fetching request header
//separate header Bearer value
public class JwtAutheticationFilter extends BasicAuthenticationFilter {
    public JwtAutheticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
          String header = request.getHeader(HttpHeaders.AUTHORIZATION);
          System.out.println("Header " + header);

            if(header != null){
                if(header.startsWith(JwtUtil.BEARER)){
                    String token = header.substring(7);
                    System.out.println("JWT token " + token);

                    //check if this token is valid
                    JwtUtil util = new JwtUtil();
                    String decodedToken = util.validateToken(token);
                    System.out.println("decodedToken " + decodedToken);

                    //fetch user claims from token
                    JsonParser parser = JsonParserFactory.getJsonParser();
                    Map<String,Object> map = parser.parseMap(decodedToken);

                    String username = map.get(JwtUtil.EMAIL_CLAIM).toString();
                    List<String> roles = (List<String>) map.get(JwtUtil.ROLES_CLAIM);
                    List<GrantedAuthority> authorities =
                            AuthorityUtils.createAuthorityList(
                                    roles);

                    //create Authentication object
                    Authentication auth;
                    auth = new UsernamePasswordAuthenticationToken(username,
                            null,
                                      authorities);

                    SecurityContext context = SecurityContextHolder.getContext();
                    context.setAuthentication(auth);
                }
            }

        //invoke next filter in chain
        chain.doFilter(request,response);

    }
}
