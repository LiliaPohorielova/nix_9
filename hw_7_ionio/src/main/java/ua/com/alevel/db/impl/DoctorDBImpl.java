package ua.com.alevel.db.impl;

import ua.com.alevel.db.DoctorDB;
import ua.com.alevel.entity.Doctor;

import java.util.ArrayList;
import java.util.UUID;

public class DoctorDBImpl implements DoctorDB {

    private final ArrayList<Doctor> doctors;
    private static DoctorDBImpl instance;

    private DoctorDBImpl() {
        doctors = new ArrayList<>();
    }

    public static DoctorDBImpl getInstance() {
        if (instance == null) {
            instance = new DoctorDBImpl();
        }
        return instance;
    }

    public void create(Doctor doctor) {
        doctor.setId(generateId());
        doctors.add(doctor);
    }

    public void update(Doctor doctor) {
        Doctor temp = findById(doctor.getId());
        temp.setName(doctor.getName());
        temp.setSpecialization(doctor.getSpecialization());
    }

    public void delete(String id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                doctors.remove(i); //TODO: НЕ ВСЕГДА ФИЗИЧЕСКОЕ УДАЛЕНИЕ
            }
        }

    }

    public Doctor findById(String id) {
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                return doctors.get(i);
            }
        }
        throw new RuntimeException("Doctor not found by id");
    }

    public ArrayList<Doctor> findAll() {
        return doctors;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (int i = 0; i < doctors.size(); i++) {
            if (doctors.get(i).getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}