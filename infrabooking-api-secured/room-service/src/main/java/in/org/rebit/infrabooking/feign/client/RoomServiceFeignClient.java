package in.org.rebit.infrabooking.feign.client;

import in.org.rebit.infrabooking.entity.Room;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

//@FeignClient(value = "email-service",url = "http://localhost:8081")
//@FeignClient(value = "email-service")
@FeignClient(value = "apigateway")
public interface RoomServiceFeignClient {

    @PostMapping("/email-service/email")
    boolean initiateEmail(@RequestBody Room r);
}
