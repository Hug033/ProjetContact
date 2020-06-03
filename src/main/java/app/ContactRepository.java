package app;

import app.model.Contact;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByLastName(String lastName);

    Contact findById(long id);
}
