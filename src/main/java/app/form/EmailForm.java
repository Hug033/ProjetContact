package app.form;

import app.model.Contact;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmailForm {

    @NotNull
    @Size(min=4, max=30)
    private String email;

    private long contact;

    public String getEmail() {
        return this.email;
    }

    public long getContact() {
        return this.contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        return "Person(Email: " + this.email + ")";
    }
}
