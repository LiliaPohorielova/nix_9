package ua.com.alevel.service.impl;

import ua.com.alevel.dao.PatientDao;
import ua.com.alevel.dao.impl.PatientDaoImpl;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.util.MyList;

public class PatientServiceImpl implements PatientService {

    private final PatientDaoImpl patientDao = new PatientDaoImpl();

    public void create(Patient patient) {
        patientDao.create(patient);
    }

    public void update(Patient patient) {
        patientDao.update(patient);
    }

    public void delete(String id) {
        patientDao.delete(id);
    }

    public Patient findById(String id) {
        try {
            return patientDao.findById(id);
        } catch (RuntimeException exception) {
            throw exception;
        }
    }

    public MyList<Patient> findAll() {
        try {
            return patientDao.findAll();
        } catch (RuntimeException exception) {
            throw exception;
        }
    }
    public void showAllToConsole() { patientDao.showAllToConsole();}
}