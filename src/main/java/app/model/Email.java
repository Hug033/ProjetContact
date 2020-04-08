package app.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Email {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String email;

    @ManyToOne
    @JoinColumn(name = "contact_fk")
    private Contact contact;

    protected Email() {}

    public Email(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Adress[id=%d, line_1='%s', line_2='%s', postalCode='%s', city='%s']",
                id, email);
    }

    public Long getId() {
        return id;
    }

    public String getLine_1() {
        return email;
    }

    public Contact getContact() {
        return contact;
    }
}