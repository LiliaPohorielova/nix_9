package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.type.DoctorSpecialization;

public class DoctorResponseDto extends ResponseDto {

    private String lastname;
    private String firstname;
    private String middleName;
    private DoctorSpecialization specialization;
    //private Integer patientCount;

    public DoctorResponseDto() { }

    public DoctorResponseDto(Doctor doctor) {
        setId(doctor.getId());
        setCreated(doctor.getCreated());
        setUpdated(doctor.getUpdated());
        this.lastname = doctor.getLastname();
        this.firstname = doctor.getFirstname();
        this.middleName = doctor.getMiddleName();
        this.specialization = doctor.getSpecialization();
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

    public DoctorSpecialization getSpecialization() {
        return specialization;
    }

    public void setSpecialization(DoctorSpecialization specialization) {
        this.specialization = specialization;
    }

   /* public Integer getPatientCount() {
        return patientCount;
    }

    public void setPatientCount(Integer patientCount) {
        this.patientCount = patientCount;
    }
    */
    @Override
    public String toString() {
        return "DoctorResponseDto{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", specialization='" + specialization + '\'' +
                '}';
    }
}