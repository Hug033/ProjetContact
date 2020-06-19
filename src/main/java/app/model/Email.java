package app.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@XmlRootElement(name = "email")
@Entity
public class Email implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String email;

    @ManyToOne
    @JoinColumn(name = "contact_fk")
    private Contact contact;

    protected Email() {}

    public Email(String email, Contact contact) {
        this.email = email;
        this.contact = contact;
    }

    @Override
    public String toString() {
        return String.format(
                "Adress[id=%d, email='%s']",
                id, email);
    }

    public Long getId() {
        return id;
    }

    @XmlElement
    public String getEmail() {
        return email;
    }

    public Contact getContact() {
        return contact;
    }
}