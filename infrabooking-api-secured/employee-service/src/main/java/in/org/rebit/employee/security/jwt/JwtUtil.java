package in.org.rebit.employee.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${security.jwt.key}")
    private String secret;
    @Value("${security.jwt.timeOut}")
    private long timeOut;

    public static final String BEARER = "Bearer";
    public static final String EMAIL_CLAIM = "EMAIL_CLAIM";
    public static final String TIMEOUT_CLAIM = "TIMEOUT_CLAIM";
    public static final String ROLES_CLAIM = "ROLES_CLAIM";

    public String createToken(String username,
                              Collection<? extends GrantedAuthority> roles){

        System.out.println("Creating token using secret key "  + secret);
        //storing roles from GrantedAuthority to String[]
        String[] rolesStr = new String[roles.size()];
        int i = 0;
        for(GrantedAuthority a : roles){
            rolesStr[i] = a.getAuthority();
            i++;
        }
        String token = JWT.create()
                .withClaim(EMAIL_CLAIM,username)
                .withClaim(TIMEOUT_CLAIM,timeOut)
                .withArrayClaim(ROLES_CLAIM,rolesStr)
                .withExpiresAt(new Date(System.currentTimeMillis() + timeOut))
                .sign(Algorithm.HMAC256(secret));
        return token;

    }



    public String validateToken(String token){
        String encodedPayload = JWT.require(Algorithm
                        .HMAC256(secret))
                .build()
                .verify(token)
                .getPayload();
        System.out.println("encodedPayload " + encodedPayload);
        byte[] bytes = Base64.getDecoder().decode(encodedPayload);
        String decodedPayload  = new String(bytes);
        System.out.println("decodedPayload " + decodedPayload);
        return decodedPayload;
    }
}
