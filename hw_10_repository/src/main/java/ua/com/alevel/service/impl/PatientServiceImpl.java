package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.persistence.repository.PatientRepository;
import ua.com.alevel.service.PatientService;

import java.util.List;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {

    private final CrudRepositoryHelper<Patient, PatientRepository> repositoryHelper;
    private final PatientRepository patientRepository;

    public PatientServiceImpl(CrudRepositoryHelper<Patient, PatientRepository> repositoryHelper, PatientRepository patientRepository) {
        this.repositoryHelper = repositoryHelper;
        this.patientRepository = patientRepository;
    }

    @Override
    public List<Doctor> getDoctors(Long id) {
        return repositoryHelper.findById(patientRepository, id).get().getDoctors();
    }

    @Override
    public void create(Patient entity) {
        repositoryHelper.create(patientRepository, entity);
    }

    @Override
    public void update(Patient entity) {
        repositoryHelper.update(patientRepository, entity);
    }

    @Override
    public void delete(Long id) {
        repositoryHelper.delete(patientRepository, id);
    }

    @Override
    public Optional<Patient> findById(Long id) {
        return repositoryHelper.findById(patientRepository, id);
    }

    @Override
    public DataTableResponse<Patient> findAll(DataTableRequest request) {
        return repositoryHelper.findAll(patientRepository, request);
    }

    @Override
    public List<Patient> findAll() {
        return patientRepository.findAll();
    }
}
