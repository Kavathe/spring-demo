package in.org.rebit.infrabooking.dao;

import in.org.rebit.infrabooking.entity.Room;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@Rollback(value = false)
@DataJpaTest
@AutoConfigureTestDatabase(replace
        = AutoConfigureTestDatabase.Replace.NONE)
public class RoomDaoTest {

    @Autowired
    private RoomDao dao;

    //@Test
    public void saveTest(){
        Room r = new Room();
        r.setType("Meeting");
        r.setAvailable(true);
        r.setCapacity(23);
        dao.save(r);

    }

    @Test
    public void findByIdTest(){
        Optional<Room> o = dao.findById(1);
        Assertions.assertTrue(o.isPresent());
    }

    @Test
    public void findByIdShouldNotReturnRoomTest(){
        Optional<Room> o = dao.findById(20);
        Assertions.assertTrue(o.isEmpty(),"Room with id " + 20 +" NOT Found");
    }
}
