package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.dao.DoctorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.service.DoctorService;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public void create(Doctor doctor) {
        doctorDao.create(doctor);
    }

    @Override
    public void update(Doctor doctor) {
        doctorDao.update(doctor);
    }

    @Override
    public void delete(Long id) {
        doctorDao.delete(id);
    }

    @Override
    public Doctor findById(Long id) {
        return doctorDao.findById(id);
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        DataTableResponse<Doctor> dataTableResponse = doctorDao.findAll(request);
        long count = doctorDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public List<Doctor> findAll() {
        List<Doctor> doctors = doctorDao.findAll();
        return doctors;
    }
}