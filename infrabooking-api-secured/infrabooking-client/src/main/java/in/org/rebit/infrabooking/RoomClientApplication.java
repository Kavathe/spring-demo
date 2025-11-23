package in.org.rebit.infrabooking;

import in.org.rebit.infrabooking.dto.EmployeeJwtToken;
import in.org.rebit.infrabooking.dto.EmployeeRequestDto;
import in.org.rebit.infrabooking.dto.RoomDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class RoomClientApplication {

    public static void main(String[] args) {
        int choice = 1;
        String tokenCache ="";
        SpringApplication.run(RoomClientApplication.class);
        Scanner sc = new Scanner(System.in);
        final String BASE_URL = "http://localhost";
        switch (choice)
        {
            case 1:
                EmployeeRequestDto request = new EmployeeRequestDto();
                request.setEmail("sandeepc@gmail.com");
                request.setPassword("sandeepc");
                EmployeeJwtToken token = RestClient.create(BASE_URL)
                        .post()
                        .uri("/employee-service/employee/login")
                        .body(request)
                        .retrieve()
                        .body(EmployeeJwtToken.class);
                System.out.println(token.getJwtToken());
                tokenCache = token.getJwtToken();

            case 2:
                  //to make HTTP REST call
                //to consume a web-service
                String data = RestClient.create(BASE_URL)
                        .get()
                        .uri("/employee-service/employee/sandeep@gmail.com")
                        .header(HttpHeaders.AUTHORIZATION,"Bearer " + tokenCache)
                        .retrieve()
                        .body(String.class);
                System.out.println(data);

                break;
            case 3:
                System.out.println("Enter Id");
                int id = sc.nextInt();

                RoomDto room = RestClient.create(BASE_URL)
                        .get()
                        .uri("/rooms/"+id)
                        .retrieve()
                        .body(RoomDto.class);

                System.out.println(room.getCapacity());
                break;

            case 4:

                RoomDto r = new RoomDto();
                r.setAvailable(true);
                r.setCapacity(10);
                r.setType("Training");


                RoomDto createdRoom = RestClient.create(BASE_URL)
                        .post()
                        .uri("/rooms")
                        .body(r)    //request
                        .retrieve()
                        .body(RoomDto.class);//response type
                System.out.println(createdRoom);
                break;
            case 5:
                     List<RoomDto> rooms = RestClient.create(BASE_URL)
                        .get()
                        .uri("/rooms")
                        .retrieve()
                        .body(new ParameterizedTypeReference<List<RoomDto>>() {
                        });
                System.out.println(rooms);


        }

    }
}
