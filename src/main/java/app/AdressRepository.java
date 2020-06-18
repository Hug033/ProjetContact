package app;

import app.form.AssignForm;
import app.model.Adress;
import app.model.Contact;
import app.model.Email;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdressRepository extends CrudRepository<Adress, Long> {

    List<Adress> findByPostalCode(String postalCode);

    Adress findById(long id);

    Adress findById(AssignForm assignForm);
}
