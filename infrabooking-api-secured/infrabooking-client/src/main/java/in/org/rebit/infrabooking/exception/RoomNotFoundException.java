package in.org.rebit.infrabooking.exception;

import org.springframework.http.ProblemDetail;

public class RoomNotFoundException extends Throwable {

    public RoomNotFoundException(int id) {
        super("Room with " + id +" NOT Found");
    }


}
