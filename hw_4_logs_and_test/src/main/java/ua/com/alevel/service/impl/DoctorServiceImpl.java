package ua.com.alevel.service.impl;

import ua.com.alevel.dao.impl.DoctorDaoImpl;
import ua.com.alevel.db.impl.DoctorDBImpl;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.service.DoctorService;
import ua.com.alevel.util.MyList;

public class DoctorServiceImpl implements DoctorService {

    private final DoctorDaoImpl doctorDao = new DoctorDaoImpl();

    public void create(Doctor doctor) {
        doctorDao.create(doctor);
    }

    public void update(Doctor doctor) {
        doctorDao.update(doctor);
    }

    public void delete(String id) {
        doctorDao.delete(id);
    }

    public Doctor findById(String id) {
        try {
            return doctorDao.findById(id);
        } catch (RuntimeException exception) {
            throw exception;
        }
    }

    public MyList<Doctor> findAll() {
        try {
            return doctorDao.findAll();
        } catch (RuntimeException exception) {
            throw exception;
        }
    }

    public void showAllToConsole() { doctorDao.showAllToConsole();}
}