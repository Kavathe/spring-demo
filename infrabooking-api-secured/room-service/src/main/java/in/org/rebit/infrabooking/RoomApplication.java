package in.org.rebit.infrabooking;

import in.org.rebit.infrabooking.entity.Room;
import in.org.rebit.infrabooking.exception.RoomNotFoundException;
import in.org.rebit.infrabooking.service.RoomService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

@SpringBootApplication
@EnableFeignClients //enables Feign client
public class RoomApplication {

    public static void main(String[] args) {

       SpringApplication.run(RoomApplication.class);


    }
}
