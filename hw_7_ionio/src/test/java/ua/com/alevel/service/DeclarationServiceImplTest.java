package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.impl.DeclarationServiceImpl;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.service.impl.PatientServiceImpl;

import java.util.ArrayList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeclarationServiceImplTest {

    private final static DeclarationServiceImpl declarationServiceImpl = new DeclarationServiceImpl();
    private final static DoctorServiceImpl doctorServiceImpl = new DoctorServiceImpl();
    private final static PatientServiceImpl patientServiceImpl = new PatientServiceImpl();
    private final static int COUNT_OF_DECLARATION = 3;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < COUNT_OF_DECLARATION; i++) {
            Doctor doctor = GenerationUtil.generateDoctor(GenerationUtil.NAME_OF_DOCTOR + i, GenerationUtil.SPECIALIZATION_OF_DOCTOR);
            doctorServiceImpl.create(doctor);
            Patient patient = GenerationUtil.generatePatient(GenerationUtil.NAME_OF_PATIENT + i, GenerationUtil.AGE_OF_PATIENT);
            patientServiceImpl.create(patient);
            Declaration declaration = GenerationUtil.generateDeclaration(patient.getId(), doctor.getId());
            declarationServiceImpl.create(declaration);
        }

        Assertions.assertEquals(COUNT_OF_DECLARATION, declarationServiceImpl.findAll().size());
    }

    @Order(1)
    @Test
    public void shouldBeFindDeclarationWhenIdIsRandom() {
        String id = getRandomIdFromDeclarationList();
        Assertions.assertNotNull(declarationServiceImpl.findById(id));
        Assertions.assertDoesNotThrow(() -> {
            declarationServiceImpl.findById(id);
        });
        verifyDeclarationListWhenDeclarationsListIsNotEmpty(COUNT_OF_DECLARATION);
    }

    @Order(2)
    @Test
    public void shouldBeUpdateDeclaration() {
        Declaration declaration = declarationServiceImpl.findById(getRandomIdFromDeclarationList());
        Doctor doctor = doctorServiceImpl.findById(getRandomIdOfDoctors());
        declaration.setIdDoctor(doctor.getId());
        Patient patient = patientServiceImpl.findById(getRandomIdOfPatients());
        declaration.setIdPatient(patient.getId());
        declarationServiceImpl.update(declaration);
        Assertions.assertEquals(declaration.getIdDoctor(), doctor.getId());
        Assertions.assertEquals(declaration.getIdPatient(), patient.getId());
        verifyDeclarationListWhenDeclarationsListIsNotEmpty(COUNT_OF_DECLARATION);
    }

    @Order(3)
    @Test
    public void shouldBeDeleteDeclaration() {
        String id = declarationServiceImpl.findAll().get(0).getId();
        declarationServiceImpl.delete(id);
        verifyDeclarationListWhenDeclarationsListIsNotEmpty(COUNT_OF_DECLARATION - 1);
    }

    @Order(4)
    @Test
    public void shouldBeEmptyArrayWhenDBIsClear() {
        GenerationUtil.clearAll();
        Assertions.assertEquals(0, declarationServiceImpl.findAll().size());
    }

    private void verifyDeclarationListWhenDeclarationsListIsNotEmpty(int size) {
        ArrayList<Declaration> declarations = declarationServiceImpl.findAll();

        Assertions.assertTrue(declarations.size() != 0);
        Assertions.assertEquals(size, declarationServiceImpl.findAll().size());
    }

    private String getRandomIdFromDeclarationList() {
        return declarationServiceImpl.findAll().get(0).getId();
    }

    private String getRandomIdOfDoctors() {
        return doctorServiceImpl.findAll().get(1).getId();
    }

    private String getRandomIdOfPatients() {
        return patientServiceImpl.findAll().get(1).getId();
    }
}
