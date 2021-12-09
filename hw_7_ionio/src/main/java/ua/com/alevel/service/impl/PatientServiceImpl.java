package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.impl.PatientDaoImpl;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.entity.Patient;
import ua.com.alevel.service.DeclarationService;
import ua.com.alevel.service.PatientService;

import java.util.ArrayList;

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
        try {
            LOGGER_INFO.info("patient start updated " + patient.getId());
            patientDao.update(patient);
            LOGGER_INFO.info("patient finish updated " + patient.getId());
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("patient NOT be updated " + patient.getId() + "; problem = " + e.getMessage());
            throw e;
        }
    }

    public void delete(String id) {
        DeclarationService declarationService = new DeclarationServiceImpl();
        ArrayList<Declaration> declarations = declarationService.findAll();
        boolean canBeDeleted = true;
        for (int i = 0; i < declarations.size(); i++) {
            if (declarations.get(i) != null && declarations.get(i).getIdPatient().equals(id)) {
                if (canBeDeleted) {
                    System.out.println("You cannot delete a patient. Delete the declaration first:");
                    canBeDeleted = false;
                }
                System.out.println(declarations.get(i).getId());
            }
        }
        if (canBeDeleted) {
            try {
                LOGGER_WARN.warn("patient start deleted " + id);
                patientDao.delete(id);
                LOGGER_WARN.warn("patient finish deleted " + id);
            } catch (RuntimeException e) {
                LOGGER_ERROR.error("patient NOT be deleted " + id + "; problem = " + e.getMessage());
                throw e;
            }
        }
    }

    public Patient findById(String id) {
        try {
            return patientDao.findById(id);
        } catch (RuntimeException exception) {
            LOGGER_ERROR.error("patient NOT be found " + id + "; problem = " + exception.getMessage());
            throw exception;
        }
    }

    public ArrayList<Patient> findAll() {
        try {
            return patientDao.findAll();
        } catch (RuntimeException exception) {
            throw exception;
        }
    }
}