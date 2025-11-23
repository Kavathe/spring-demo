package in.org.rebit.infrabooking.service;

import in.org.rebit.infrabooking.dao.RoomDao;
import in.org.rebit.infrabooking.entity.Room;
import in.org.rebit.infrabooking.exception.RoomNotFoundException;

import java.util.List;

public interface RoomService {
    List<Room> getAllAvailableRooms(boolean available);
    Room registerRoom(Room room);
    List<Room> getRoomsByAvailablityAndCapacity(boolean available, int capacity);
    void removeRoomById(int id) throws RoomNotFoundException;
    List<Room> getAllRooms();
    Room getRoomById(int id) throws RoomNotFoundException;
    Room updateRoom(int id, Room r) throws RoomNotFoundException;

}