package in.org.rebit.email.rest.controller;

import in.org.rebit.email.dto.RoomDto;
import in.org.rebit.email.entity.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailRestController {

    @Autowired
    private org.rebit.email.service.EmailService service;

    @PostMapping
    public boolean sendEmail(@RequestBody RoomDto r,SecurityContext context){
        System.out.println("email sent");
        String loggedEmail = context.getAuthentication().getName();
        //loggedin employee email
        System.out.println(loggedEmail);

        Email e = new Email();
        e.setPurpose("Room Created");
        e.setRoomId(r.getId());
        e.setEmployeeEmail(loggedEmail);
        service.saveEmail(e);

        return true;
    }

    @GetMapping()
    public Email getmailOnly{
        return this.service.getAllMails();
    }
}
