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
    private String phone;
    private String civility;
    private String linkImage;

    @ManyToMany
    @JoinTable(name="ADDRESSES")
    private Collection<Adress> adresses;

    @OneToMany(mappedBy = "contact")
    private Collection<Email> emails;

    protected Contact() {}

    public Contact(String firstName, String lastName, String phone, String civility, String linkImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.civility = civility;
        this.linkImage = linkImage;
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

    public String getPhone() {
        return phone;
    }

    public String getCivility() {
        return civility;
    }

    public String getLinkImage() { return linkImage; }

    public Collection<Adress> getAdresses() { return adresses; }

    public Collection<Email> getEmails() { return emails; }
}