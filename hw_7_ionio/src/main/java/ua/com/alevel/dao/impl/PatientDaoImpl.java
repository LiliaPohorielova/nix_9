package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.PatientDao;
import ua.com.alevel.db.impl.PatientDBImpl;
import ua.com.alevel.entity.Patient;

import java.util.ArrayList;

public class PatientDaoImpl implements PatientDao {

    public void create(Patient patient) {
        PatientDBImpl.getInstance().create(patient);
    }

    public void update(Patient patient) {
        PatientDBImpl.getInstance().update(patient);
    }

    public void delete(String id) {
        PatientDBImpl.getInstance().delete(id);
    }

    public Patient findById(String id) {
        return PatientDBImpl.getInstance().findById(id);
    }

    public ArrayList<Patient> findAll() {
        return PatientDBImpl.getInstance().findAll();
    }
}