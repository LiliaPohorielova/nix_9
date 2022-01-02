package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.exception.EntityNotFoundException;
import ua.com.alevel.persistence.dao.PatientDao;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.service.PatientService;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    @Override
    public List<Doctor> getDoctors(Long id) {
        return patientDao.getDoctors(id);
    }

    @Override
    public void create(Patient patient) {
        patientDao.create(patient);
    }

    @Override
    public void update(Patient entity) {
        if (!patientDao.existById(entity.getId())) {
            throw new EntityNotFoundException("patient not found");
        }
        patientDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        if (!patientDao.existById(id)) {
            throw new EntityNotFoundException("patient not found");
        }
        patientDao.delete(id);
    }

    @Override
    public Patient findById(Long id) {
        if (!patientDao.existById(id)) {
            throw new EntityNotFoundException("patient not found");
        }
        return patientDao.findById(id);
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        DataTableResponse<Patient> dataTableResponse = patientDao.findAll(request);
        long count = patientDao.count();
        dataTableResponse.setItemsSize(count);
        return dataTableResponse;
    }

    @Override
    public List<Patient> findAll() {
        List<Patient> patients = patientDao.findAll();
        return patients;
    }
}
