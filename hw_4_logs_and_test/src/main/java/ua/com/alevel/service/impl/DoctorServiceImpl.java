package ua.com.alevel.service.impl;

import ua.com.alevel.dao.impl.DoctorDaoImpl;
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
        DoctorService doctorService = new DoctorServiceImpl();
        MyList<Doctor> doctors = doctorService.findAll();
        boolean canBeDeleted = true;
        for (int i = 0; i < doctors.getCountOfEntities(); i++) {
            if (doctors.getEntity(i) != null && doctors.getEntity(i).getId().equals(id)) {
                if (canBeDeleted) {
                    System.out.println("You cannot delete a patient. Delete the declaration first:");
                    canBeDeleted = false;
                }
                System.out.println(doctors.getEntity(i).getId());
            }
        }
        if (canBeDeleted) {
            try {
                doctorDao.delete(id);
            } catch (RuntimeException e) {
                throw e;
            }
        }
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
}