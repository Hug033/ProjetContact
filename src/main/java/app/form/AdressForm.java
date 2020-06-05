package app.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AdressForm {

    private Long id;

    @NotNull
    private String line_1;

    private String line_2;

    @NotNull
    @Size(min=5, max=5)
    private String postalCode;

    @NotNull
    private String city;

    private long contact;

    public String getLine_1() {
        return this.line_1;
    }

    public String getLine_2() {
        return this.line_2;
    }

    public String getPostalCode() {
        return this.postalCode;
    }

    public String getCity() {
        return this.city;
    }

    public void setLine_1(String line_1) {
        this.line_1 = line_1;
    }

    public void setLine_2(String line_2) {
        this.line_2 = line_2;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getContact() {
        return this.contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public String toString() {
        return "Adresse(Id: " + this.id + ")";
    }
}
