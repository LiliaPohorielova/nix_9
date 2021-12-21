package ua.com.alevel.persistence.entity;

public class Declaration extends BaseEntity {

    public Long patientId;
    public Long doctorId;

    public Declaration() {
        super();
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "ID: " + super.getId() + "\n" +
                "Patient: " + patientId + "\n" +
                "Doctor: " + doctorId + "\n";
    }
}
