package in.org.rebit.infrabooking.dao;

import in.org.rebit.infrabooking.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RoomDao extends JpaRepository<Room,Integer> {

    List<Room> findByAvailable(boolean available);
    List<Room> findByAvailableAndCapacity(boolean available,int capacity);
    @Query("SELECT DISTINCT type FROM Room")
    List<String> findUniqueRooms();
}
