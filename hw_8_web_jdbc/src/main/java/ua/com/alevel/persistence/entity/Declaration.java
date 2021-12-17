package ua.com.alevel.persistence.entity;

public class Declaration extends BaseEntity {

    public String idPatient;
    public String idDoctor;

    public Declaration() {
        super();
    }

    public String getIdPatient() {
        return idPatient;
    }

    public void setIdPatient(String idPatient) {
        this.idPatient = idPatient;
    }

    public String getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(String idDoctor) {
        this.idDoctor = idDoctor;
    }

    @Override
    public String toString() {
        return "ID: " + super.getId() + "\n" +
                "Patient: " + idPatient + "\n" +
                "Doctor: " + idDoctor + "\n";
    }
}
