package app.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Contact {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;

    @ManyToMany
    @JoinTable(name="ADDRESSES")
    private Collection<Adress> adresses;

    @OneToMany(mappedBy = "contact")
    private Collection<Email> emails;

    protected Contact() {}

    public Contact(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer[id=%d, firstName='%s', lastName='%s']",
                id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Collection<Adress> getAdresses() { return adresses; }

    public Collection<Email> getEmails() { return emails; }
}