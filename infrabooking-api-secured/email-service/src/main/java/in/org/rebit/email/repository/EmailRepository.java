package in.org.rebit.email.repository;

import in.org.rebit.email.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Integer> {


}
