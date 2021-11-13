package ua.com.alevel.dao.impl;

import ua.com.alevel.dao.DoctorDao;
import ua.com.alevel.db.impl.DoctorDBImpl;
import ua.com.alevel.entity.Doctor;
import ua.com.alevel.util.MyList;

public class DoctorDaoImpl implements DoctorDao {

    public void create(Doctor doctor) {
        DoctorDBImpl.getInstance().create(doctor);
    }

    public void update(Doctor doctor) {
        DoctorDBImpl.getInstance().update(doctor);
    }

    public void delete(String id) {
        DoctorDBImpl.getInstance().delete(id);
    }

    public Doctor findById(String id) {
        return DoctorDBImpl.getInstance().findById(id);
    }

    public MyList<Doctor> findAll() {
        return DoctorDBImpl.getInstance().findAll();
    }
}
