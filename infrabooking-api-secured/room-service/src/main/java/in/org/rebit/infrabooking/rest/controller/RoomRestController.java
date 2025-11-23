package in.org.rebit.infrabooking.rest.controller;

import in.org.rebit.infrabooking.entity.Room;
import in.org.rebit.infrabooking.exception.RoomNotFoundException;
import in.org.rebit.infrabooking.service.RoomService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

//@Controller
@RefreshScope
@RestController
@RequestMapping("/rooms") //common portion of URI
public class RoomRestController {
    @Autowired
    private RoomService service;

    @Value("${security.jwt.key}")
    private String jwtKey;

    @GetMapping("/demo")
    private String demo() {

        System.out.println("In demo() method of RoomRestCOntroller");
        return jwtKey;
    }



    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteRoomById(@PathVariable int id) throws RoomNotFoundException {
        this.service.removeRoomById(id);
    }
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable int id,
                           @RequestBody Room r) throws RoomNotFoundException {
        return this.service.updateRoom(id,r);
    }

    //@ResponseBody
    @GetMapping()
    @ResponseStatus(code = HttpStatus.OK)
    public List<Room> searchAll(){
        return this.service.getAllRooms();
    }

    //@ResponseBody
    @ResponseStatus(code = HttpStatus.OK)
    @GetMapping("/{id}")
    public Room searchById(@PathVariable int id) throws RoomNotFoundException {
        System.out.println(this.service.getClass());
        return this.service.getRoomById(id);
    }

    @GetMapping(params = "capacity")
    public List<Room> searchRoomsByCapacity(@RequestParam
                                                int capacity){
        return this.service.getRoomsByAvailablityAndCapacity(true,capacity);
    }

    @GetMapping(value = "/demo",params = {"id","type"})
    public String demo(@RequestParam int id,@RequestParam String type){
        return id + "     "+type;
    }



    @ResponseStatus(code = HttpStatus.CREATED)
    @PostMapping()
    public Room createNewRoom(@Valid @RequestBody Room r){
        System.out.println(r);
        Room createdRoom =  this.service.registerRoom(r);
        System.out.println(createdRoom);
        System.out.println("==============================");
        return createdRoom;
    }

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(RoomNotFoundException.class)
    public ProblemDetail handleRoomNotFoundException(RoomNotFoundException e){
        ProblemDetail p = ProblemDetail.forStatus(404);
      //  p.setInstance(URI.create("/rooms"));
      //  p.setDetail(e.getMessage());
        System.out.println("==handleRoomNotFoundException");
        return p;
    }












}
