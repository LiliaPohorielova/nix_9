package ua.com.alevel.persistence.entity;

import ua.com.alevel.type.DoctorSpecialization;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "doctors")
public class Doctor extends BaseEntity {

    private String lastname;
    private String firstname;
    private String middleName;

    @Enumerated(EnumType.STRING)
    @Column(name = "specialization")
    private DoctorSpecialization specialization;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REMOVE }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "declaration",
            joinColumns = @JoinColumn(name = "doctor"),
            inverseJoinColumns = @JoinColumn(name = "patient"))
    private List<Patient> patients;

    public Doctor() {
        super();
        patients = new ArrayList<>();
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

    public List<Patient> getPatients() {
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
        patient.getDoctors().add(this);
    }

    public void removePatient(Patient patient) {
        patients.remove(patient);
        patient.getDoctors().remove(this);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "lastname='" + lastname + '\'' +
                ", firstname='" + firstname + '\'' +
                ", middleName='" + middleName + '\'' +
                ", specialization=" + specialization +
                ", patients=" + patients +
                '}';
    }
}
