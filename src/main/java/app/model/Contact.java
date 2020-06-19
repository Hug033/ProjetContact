package app.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@XmlRootElement(name = "contact")
@Entity
public class Contact implements Serializable {

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

    @XmlElement
    public Long getId() {
        return id;
    }

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    @XmlElement
    public String getLastName() {
        return lastName;
    }

    @XmlElement
    public String getPhone() {
        return phone;
    }

    @XmlElement
    public String getCivility() {
        return civility;
    }

    @XmlElement
    public String getLinkImage() { return linkImage; }

    @XmlElement
    public Collection<Adress> getAdresses() { return adresses; }

    @XmlElement
    public Collection<Email> getEmails() { return emails; }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setCivility(String civility) {
        this.civility = civility;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public void setAdresses(Collection<Adress> adresses) { this.adresses = adresses; };

    public void setEmails(Collection<Email> emails) { this.emails = emails; };
}