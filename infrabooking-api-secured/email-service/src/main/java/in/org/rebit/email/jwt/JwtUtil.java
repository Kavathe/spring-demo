package in.org.rebit.email.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import java.util.Base64;

public class JwtUtil {

    private String secret = "secret_key";
    private long timeOut = 60000;

    public static final String EMAIL_CLAIM = "EMAIL_CLAIM";
    public static final String BEARER = "Bearer";
    public static final String ROLES_CLAIM = "ROLES_CLAIM";


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
