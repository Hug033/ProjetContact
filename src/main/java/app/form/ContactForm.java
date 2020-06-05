package app.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ContactForm {

    private long id;

    @NotNull
    @Size(min=2, max=30)
    private String firstName;

    private String civility;
    private String phone;
    private String linkImage;
    private String email;

    @NotNull
    @Size(min=2, max=30)
    private String lastName;

    public String getFirstName() {
        return this.firstName;
    }

    public long getId() {
        return this.id;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getCivility() {
        return this.civility;
    }

    public String getLinkImage() {
        return this.linkImage;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String toString() {
        return "Person(Firstname: " + this.firstName  + " LasName: " + this.lastName + ")";
    }
}
