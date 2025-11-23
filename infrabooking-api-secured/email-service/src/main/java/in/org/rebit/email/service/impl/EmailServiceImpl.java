package in.org.rebit.email.service.impl;

import in.org.rebit.email.entity.Email;
import in.org.rebit.email.repository.EmailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements org.rebit.email.service.EmailService {

    @Autowired
    private EmailRepository repo;

    @Override
    public Email saveEmail(Email e) {
        return repo.save(e);
    }
}
