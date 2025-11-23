package in.org.rebit.infrabooking.feign.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import in.org.rebit.infrabooking.security.jwt.JwtUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;


@Component
public class RoomFeignClientRequestInterceptor implements RequestInterceptor {

    //this method is automatically invoked when feign client makes a request
    @Override
    public void apply(RequestTemplate requestTemplate) {

        System.out.println("in apply method of RoomFeignClientRequestInterceptor " + JwtUtil.token);
        requestTemplate.header(HttpHeaders.AUTHORIZATION,
                JwtUtil.BEARER +" "+ JwtUtil.token);
    }
}