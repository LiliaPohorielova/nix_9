package ua.com.alevel.entity;

public class Declaration extends BaseEntity {

    private String idPatient;
    private String idDoctor;

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
        return "Declaration{" +
                "id='" + super.getId() + '\'' +
                ", patient='" + idPatient + '\'' +
                ", id of doctor= '" + idDoctor +
                "'}";
    }
}
