package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.impl.PatientServiceImpl;
import ua.com.alevel.util.MyList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientServiceImplTest {

    private final static PatientServiceImpl patientServiceImpl = new PatientServiceImpl();
    private final static int COUNT_OF_PATIENTS = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < COUNT_OF_PATIENTS; i++) {
            Patient patient = PatientGenerationUtil.generatePatient(PatientGenerationUtil.NAME_OF_PATIENT + i, PatientGenerationUtil.AGE_OF_PATIENT);
            patientServiceImpl.create(patient);
        }

        Assertions.assertEquals(COUNT_OF_PATIENTS, patientServiceImpl.findAll().getCountOfEntities());
    }

    @Order(1)
    @Test
    public void shouldBeCreatePatientWhenNameIsEmpty() {
        Patient Patient = new Patient();
        Patient.setName(null);
        patientServiceImpl.create(Patient);
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_PATIENTS + 1);
    }

    @Order(2)
    @Test
    public void shouldBeFindPatientByIdWhenPatientExistInDB() {
        String id = patientServiceImpl.findAll().getEntity(0).getId();

        Assertions.assertNotNull(patientServiceImpl.findById(id));
        Assertions.assertDoesNotThrow(() -> {
            patientServiceImpl.findById(id);
        });
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_PATIENTS + 1);
    }

    @Order(3)
    @Test
    public void shouldBeUpdatePatientWhenPatientExistInDB() {
        String id = patientServiceImpl.findAll().getEntity(0).getId();
        Patient patient = new Patient();
        patient.setId(id);
        String newName = PatientGenerationUtil.NAME_OF_PATIENT + PatientGenerationUtil.NAME_OF_PATIENT;
        patient.setName(newName);
        patientServiceImpl.update(patient);
        Patient patientById = patientServiceImpl.findById(id);

        Assertions.assertEquals(patientById.getName(), newName);
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_PATIENTS + 1);
    }

    @Order(4)
    @Test
    public void shouldBeDeletePatientWhenPatientAgreementDoesNotExist() {
        String id = patientServiceImpl.findAll().getEntity(0).getId();
        patientServiceImpl.delete(id);
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_PATIENTS);
    }

    /*@Order(5)
    @Test
    public void shouldBeNotDeletePatientWhenPatientAgreementIsExist() {
        String id = patientServiceImpl.getEntity(0).getId();
        Patient Patient = patientServiceImpl.findById(id);
        Patient Patient = PatientGenerationUtil.generateCustomer(PatientGenerationUtil.NAME_CUSTOMER);
        Patient.create(customer);
        Patient patient = PatientGenerationUtil.generateCustomerAgreement(GenerationUtil.NAME_CUSTOMER_AGREEMENT, Patient, customer);
        Patient.create(customerAgreement);
        patientServiceImpl.delete(id);
        verifyPatientArrayWhenPatientsListIsNotEmpty(COUNT_OF_PATIENTS);
    }

    @Order(6)
    @Test
    public void shouldBeEmptyArrayWhenDBIsClear() {
        GenerationUtil.clearDB();

        Assertions.assertEquals(0, patientServiceImpl.findAll().length);
    }*/

    private void verifyPatientArrayWhenPatientsListIsNotEmpty(int size) {
        MyList<Patient> patients = patientServiceImpl.findAll();

        Assertions.assertTrue(patients.getCountOfEntities() != 0);
        Assertions.assertEquals(size, patientServiceImpl.findAll().getCountOfEntities());
    }
}
