package app.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class AssignForm {

    @NotNull
    private String adressId;

    public String getAdressId() {
        return this.adressId;
    }

    public long getAdressIdLong() { return Long.valueOf(this.adressId); }

    public void setAdressId(String adressId) {
        this.adressId = adressId;
    }

    public String toString() {
        return "Adresse(Id: " + this.adressId + ")";
    }
}
