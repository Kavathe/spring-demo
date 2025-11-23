package in.org.rebit.infrabooking.service.impl;

import in.org.rebit.infrabooking.dao.RoomDao;
import in.org.rebit.infrabooking.entity.Room;
import in.org.rebit.infrabooking.exception.RoomNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class RoomServiceImplTest {

    @InjectMocks
    private RoomServiceImpl roomService;

    @Mock
    private RoomDao dao;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void findRoomTest()
    {


    }

}
