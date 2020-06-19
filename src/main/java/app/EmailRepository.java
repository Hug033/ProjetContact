package app;

import app.model.Contact;
import app.model.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Long> {

    Email findByEmail(String email);

    Email findById(long id);
}
