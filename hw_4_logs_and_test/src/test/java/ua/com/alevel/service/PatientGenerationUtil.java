package ua.com.alevel.service;

import ua.com.alevel.entity.Patient;

public class PatientGenerationUtil {
    public static final String NAME_OF_PATIENT = "test patient";
    public static final int AGE_OF_PATIENT = 25;

    public static Patient generatePatient() {
        Patient Patient = new Patient();
        Patient.setName(NAME_OF_PATIENT);
        Patient.setAge(AGE_OF_PATIENT);
        return Patient;
    }

    public static Patient generatePatient(String name, int age) {
        Patient Patient = new Patient();
        Patient.setName(name);
        Patient.setAge(age);
        return Patient;
    }

    public static Patient generatePatient(int age) {
        Patient Patient = new Patient();
        Patient.setName(NAME_OF_PATIENT);
        Patient.setAge(age);
        return Patient;
    }
}
