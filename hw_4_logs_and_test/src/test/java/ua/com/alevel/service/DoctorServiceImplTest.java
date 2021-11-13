package ua.com.alevel.service;

import org.junit.jupiter.api.*;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.service.impl.DoctorServiceImpl;
import ua.com.alevel.util.MyList;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DoctorServiceImplTest {

    private final static DoctorServiceImpl doctorServiceImpl = new DoctorServiceImpl();
    private final static int COUNT_OF_DOCTORS = 10;

    @BeforeAll
    public static void setUp() {
        for (int i = 0; i < COUNT_OF_DOCTORS; i++) {
            Doctor doctor = GenerationUtil.generateDoctor(GenerationUtil.NAME_OF_DOCTOR + i, GenerationUtil.SPECIALIZATION_OF_DOCTOR);
            doctorServiceImpl.create(doctor);
        }

        Assertions.assertEquals(COUNT_OF_DOCTORS, doctorServiceImpl.findAll().getCountOfEntities());
    }

    @Order(1)
    @Test
    public void shouldBeCreateDoctorWhenNameIsEmpty() {
        Doctor doctor = new Doctor();
        doctor.setName(null);
        doctor.setSpecialization(GenerationUtil.SPECIALIZATION_OF_DOCTOR);
        doctorServiceImpl.create(doctor);
        verifyDoctorListWhenDoctorsListIsNotEmpty(COUNT_OF_DOCTORS + 1);
    }

    @Order(2)
    @Test
    public void shouldBeDeleteDoctor() {
        String id = doctorServiceImpl.findAll().getEntity(0).getId();
        doctorServiceImpl.delete(id);
        verifyDoctorListWhenDoctorsListIsNotEmpty(COUNT_OF_DOCTORS);
    }

    @Order(3)
    @Test
    public void shouldBeFindDoctorWhenIdIsRandom() {
        Doctor doctor = getRandomDoctorFromDoctorList(getRandomIdFromDoctorList());
        Assertions.assertNotNull(doctor);
    }

    @Order(4)
    @Test
    public void shouldBeUpdateDoctor() {
        String id = getRandomIdFromDoctorList();
        Doctor doctor = getRandomDoctorFromDoctorList(id);
        doctor.setSpecialization(GenerationUtil.SPECIALIZATION_OF_DOCTOR);
        doctor.setName("test");
        doctorServiceImpl.update(doctor);
        doctor = doctorServiceImpl.findById(id);
        Assertions.assertEquals("test", doctor.getName());
        Assertions.assertEquals(GenerationUtil.SPECIALIZATION_OF_DOCTOR, doctor.getSpecialization());
    }

    @Order(5)
    @Test
    public void shouldBeEmptyArrayWhenDBIsClear() {
        GenerationUtil.clearDoctors();
        Assertions.assertEquals(0, doctorServiceImpl.findAll().getCountOfEntities());
    }

    private void verifyDoctorListWhenDoctorsListIsNotEmpty(int size) {
        MyList<Doctor> doctors = doctorServiceImpl.findAll();

        Assertions.assertTrue(doctors.getCountOfEntities() != 0);
        Assertions.assertEquals(size, doctorServiceImpl.findAll().getCountOfEntities());
    }

    private String getRandomIdFromDoctorList() {
        return doctorServiceImpl.findAll().getEntity(0).getId();
    }

    private Doctor getRandomDoctorFromDoctorList(String id) {
        return doctorServiceImpl.findById(id);
    }
}
