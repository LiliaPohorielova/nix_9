package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Doctor;

public class DoctorResponseDto extends ResponseDto {

    private String lastname;
    private String firstname;
    private String middleName;
    private String specialization;
    private Integer patientCount;

    public DoctorResponseDto() { }

    public DoctorResponseDto(Doctor doctor) {
        setId(doctor.getId());
        setCreated(doctor.getCreated());
        setUpdated(doctor.getUpdated());
        setVisible(doctor.getVisible());
        this.lastname = doctor.getLastname();
        this.firstname = doctor.getFirstname();
        this.middleName = doctor.getMiddleName();
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public Integer getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(Integer patientCount) {
        this.patientCount = patientCount;
    }

    @Override
    public String toString() {
        return "DoctorResponseDto{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", specialization='" + specialization + '\'' +
                ", patientCount=" + patientCount +
                '}';
    }
}