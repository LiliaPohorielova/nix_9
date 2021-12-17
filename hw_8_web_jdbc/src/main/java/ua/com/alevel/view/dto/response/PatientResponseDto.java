package ua.com.alevel.view.dto.response;

import ua.com.alevel.persistence.entity.Patient;

public class PatientResponseDto extends ResponseDto {

    private String firstname;
    private String lastname;
    private Integer age;

    public PatientResponseDto() { }

    public PatientResponseDto(Patient patient) {
        setId(patient.getId());
        setCreated(patient.getCreated());
        setUpdated(patient.getUpdated());
        setVisible(patient.getVisible());
        this.firstname = patient.getFirstname();
        this.lastname = patient.getLastname();
        this.age = patient.getAge();;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public Integer getAge() {
        return age;
    }
}