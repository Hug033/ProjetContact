package app;

import app.model.Contact;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name="contacts")
public class ContactList implements Serializable {

    private List<Contact> contacts = new ArrayList<Contact>();

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
