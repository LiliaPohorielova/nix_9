package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.impl.PatientDaoImpl;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.PatientService;
import ua.com.alevel.util.MyList;

public class PatientServiceImpl implements PatientService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final PatientDaoImpl patientDao = new PatientDaoImpl();

    public void create(Patient patient) {
        LOGGER_INFO.info("patient start created");
        patientDao.create(patient);
        LOGGER_INFO.info("patient finish created, id: " + patient.getId());
    }

    public void update(Patient patient) {
        patientDao.update(patient);
    }

    public void delete(String id) {
        PatientService patientService = new PatientServiceImpl();
        MyList<Patient> patients = patientService.findAll();
        boolean canBeDeleted = true;
        for (int i = 0; i < patients.getCountOfEntities(); i++) {
            if (patients.getEntity(i) != null && patients.getEntity(i).getId().equals(id)) {
                if (canBeDeleted) {
                    System.out.println("You cannot delete a patient. Delete the declaration first:");
                    canBeDeleted = false;
                }
                System.out.println(patients.getEntity(i).getId());
            }
        }
        if (canBeDeleted) {
            try {
                patientDao.delete(id);
            } catch (RuntimeException e) {
                throw e;
            }
        }
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
}