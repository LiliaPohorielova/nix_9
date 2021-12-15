package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.impl.PatientServiceImpl;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientServiceImplTest {

    private final static PatientServiceImpl patientServiceImpl = new PatientServiceImpl();
    private final static int COUNT_OF_PATIENTS = 3;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < COUNT_OF_PATIENTS; i++) {
            Patient patient = GenerationUtil.generatePatient(GenerationUtil.NAME_OF_PATIENT + i, GenerationUtil.AGE_OF_PATIENT);
            patientServiceImpl.create(patient);
        }

        Assertions.assertEquals(COUNT_OF_PATIENTS, patientServiceImpl.findAll().size());
    }

    @Order(1)
    @Test
    public void shouldBeFindPatientWhenIdIsRandom() {
        Patient patient = getRandomPatientFromPatientList(getRandomIdFromPatientList());
        Assertions.assertNotNull(patient);
    }

    @Order(2)
    @Test
    public void shouldBeUpdatePatient() {
        String id = getRandomIdFromPatientList();
        Patient patient = getRandomPatientFromPatientList(id);
        patient.setAge(99);
        patient.setName("test");
        patientServiceImpl.update(patient);
        patient = patientServiceImpl.findById(id);
        Assertions.assertEquals("test", patient.getName());
        Assertions.assertEquals(99, patient.getAge());
    }

    @Order(3)
    @Test
    public void shouldBeDeletePatient() {
        String id = patientServiceImpl.findAll().get(0).getId();
        patientServiceImpl.delete(id);
        verifyPatientListWhenPatientsListIsNotEmpty(COUNT_OF_PATIENTS-1);
    }

    @Order(4)
    @Test
    public void shouldBeEmptyArrayWhenDBIsClear() {
        GenerationUtil.clearPatients();
        Assertions.assertEquals(0, patientServiceImpl.findAll().size());
    }

    private void verifyPatientListWhenPatientsListIsNotEmpty(int size) {
        ArrayList<Patient> patients = patientServiceImpl.findAll();

        Assertions.assertTrue(patients.size() != 0);
        Assertions.assertEquals(size, patientServiceImpl.findAll().size());
    }

    private String getRandomIdFromPatientList() {
        return patientServiceImpl.findAll().get(0).getId();
    }

    private Patient getRandomPatientFromPatientList(String id) {
        return patientServiceImpl.findById(id);
    }
}
