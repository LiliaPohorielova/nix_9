package ua.com.alevel.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.impl.DoctorDaoImpl;
import ua.com.alevel.entity.Declaration;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.service.DoctorService;

import java.util.ArrayList;

public class DoctorServiceImpl implements DoctorService {

    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");
    private static final Logger LOGGER_ERROR = LoggerFactory.getLogger("error");
    private final DoctorDaoImpl doctorDao = new DoctorDaoImpl();

    public void create(Doctor doctor) {
        LOGGER_INFO.info("doctor start created");
        doctorDao.create(doctor);
        LOGGER_INFO.info("doctor finish created, id: " + doctor.getId());
    }

    public void update(Doctor doctor) {
        try {
            LOGGER_INFO.info("doctor start updated, id: " + doctor.getId());
            doctorDao.update(doctor);
            LOGGER_INFO.info("doctor finish updated, id: " + doctor.getId());
        } catch (RuntimeException e) {
            LOGGER_ERROR.error("doctor NOT be updated " + doctor.getId() + "; problem = " + e.getMessage());
            throw e;
        }
    }

    public void delete(String id) {
        DeclarationServiceImpl declarationService = new DeclarationServiceImpl();
        ArrayList<Declaration> declarations = declarationService.findAll();
        boolean canBeDeleted = true;
        for (int i = 0; i < declarations.size(); i++) {
            if (declarations.get(i) != null && declarations.get(i).getIdDoctor().equals(id)) {
                if (canBeDeleted) {
                    System.out.println("You cannot delete a doctor. Delete the declaration first:");
                    canBeDeleted = false;
                }
                System.out.println(declarations.get(i).getId());
            }
        }
        if (canBeDeleted) {
            try {
                LOGGER_WARN.warn("doctor start deleted " + id);
                doctorDao.delete(id);
                LOGGER_WARN.warn("doctor finish deleted " + id);
            } catch (RuntimeException e) {
                LOGGER_ERROR.error("doctor NOT be deleted " + id + "; problem = " + e.getMessage());
                throw e;
            }
        }
    }

    public Doctor findById(String id) {
        try {
            return doctorDao.findById(id);
        } catch (RuntimeException exception) {
            LOGGER_ERROR.error("doctor NOT be found " + id + "; problem = " + exception.getMessage());
            throw exception;
        }
    }

    public ArrayList<Doctor> findAll() {
        try {
            return doctorDao.findAll();
        } catch (RuntimeException exception) {
            throw exception;
        }
    }
}