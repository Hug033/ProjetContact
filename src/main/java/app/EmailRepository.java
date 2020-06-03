package app;

import app.model.Contact;
import app.model.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmailRepository extends CrudRepository<Email, Long> {

    List<Contact> findByEmail(String email);

    Contact findById(long id);
}
