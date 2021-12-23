package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.DoctorDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.service.DoctorService;

import java.util.List;
import java.util.Set;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    public DoctorServiceImpl(
            DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Override
    public void addPatient(Long doctorId, Long patientId) {
        doctorDao.addPatient(doctorId, patientId);
    }

    @Override
    public void removePatient(Long doctorId, Long patientId) {
        doctorDao.removePatient(doctorId, patientId);
    }

    @Override
    public List<Patient> getPatients(Long id) {
        return doctorDao.getPatients(id);
    }

    @Override
    public void create(Doctor doctor) {
        doctorDao.create(doctor);
    }

    @Override
    public void update(Doctor entity) {
        if (!doctorDao.existById(entity.getId())) {
            throw new EntityNotFoundException("doctor not found");
        }
        doctorDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!doctorDao.existById(id)) {
            throw new EntityNotFoundException("doctor not found");
        }
        doctorDao.delete(id);
    }

    @Override
    public Doctor findById(Long id) {
        if (!doctorDao.existById(id)) {
            throw new EntityNotFoundException("doctor not found");
        }
        return doctorDao.findById(id);
    }

    @Override
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        DataTableResponse<Doctor> dataTableResponse = doctorDao.findAll(request);
        long count = doctorDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }
}
