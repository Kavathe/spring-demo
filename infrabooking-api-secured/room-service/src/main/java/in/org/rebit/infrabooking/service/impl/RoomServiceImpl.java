package in.org.rebit.infrabooking.service.impl;

import in.org.rebit.infrabooking.dao.RoomDao;
import in.org.rebit.infrabooking.entity.Room;
import in.org.rebit.infrabooking.exception.RoomNotFoundException;
import in.org.rebit.infrabooking.feign.client.RoomServiceFeignClient;
import in.org.rebit.infrabooking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Optional;

@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomDao repo;

    @Autowired
    private RoomServiceFeignClient cilent;

    @Override
    public List<Room> getAllAvailableRooms(boolean available) {
        return repo.findByAvailable(available);
    }

    @Override
    public Room registerRoom(Room room) {
        Room createdRoom = this.repo.save(room);
        if(createdRoom !=null){
            System.out.println("Sending email");
            boolean isEmailSent = cilent.initiateEmail(createdRoom);
            System.out.println(isEmailSent);
        }
        return createdRoom;
    }

    @Override
    public List<Room> getRoomsByAvailablityAndCapacity(boolean available, int capacity) {
        return this.repo.findByAvailableAndCapacity(available,capacity);
    }



    public void removeRoomById(int id) throws RoomNotFoundException {
        Optional<Room> o = this.repo.findById(id);

        if (o.isPresent()) {
            this.repo.deleteById(id);
        } else {
            throw new RoomNotFoundException(id);
        }
    }
    @Override
    public List<Room> getAllRooms() {
        return this.repo.findAll();
    }
    @Override
    public Room getRoomById(int id) throws RoomNotFoundException {
        return this.repo.findById(id)
                .orElseThrow(()-> new RoomNotFoundException(id));
    }

    @Override
    public Room updateRoom(int id, Room r) throws RoomNotFoundException {
        Optional<Room> o = this.repo.findById(id);
        if(o.isPresent()){
            r.setId(id);
            return this.repo.save(r);
        }
        throw new RoomNotFoundException(id);
    }

}