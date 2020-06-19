package app.model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.Collection;

@XmlRootElement(name = "address")
@Entity
public class Adress implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String line_1;
    private String line_2;
    private String postalCode;
    private String city;

    @ManyToMany(mappedBy = "adresses", cascade = CascadeType.REMOVE)
    private Collection<Contact> contacts;

    protected Adress() {}

    public Adress(String line_1, String line_2, String postalCode, String city) {
        this.line_1 = line_1;
        this.line_2 = line_2;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public String toString() {
        return String.format(
                "Adress[id=%d, line_1='%s', line_2='%s', postalCode='%s', city='%s']",
                id, line_1, line_2, postalCode, city);
    }

    public Long getId() {
        return id;
    }

    @XmlElement
    public String getLine_1() {
        return line_1;
    }

    @XmlElement
    public String getLine_2() {
        return line_2;
    }

    @XmlElement
    public String getPostalCode() {
        return postalCode;
    }

    @XmlElement
    public String getCity() {
        return city;
    }

    public Collection<Contact> getContacts() {
        return contacts;
    }
}