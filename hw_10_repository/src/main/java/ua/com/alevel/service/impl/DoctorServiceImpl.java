package ua.com.alevel.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import ua.com.alevel.persistence.crud.CrudRepositoryHelper;
import ua.com.alevel.persistence.datatable.DataTableRequest;
import ua.com.alevel.persistence.datatable.DataTableResponse;
import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.persistence.repository.DoctorRepository;
import ua.com.alevel.persistence.repository.PatientRepository;
import ua.com.alevel.service.DoctorService;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final CrudRepositoryHelper<Doctor, DoctorRepository> doctorRepositoryHelper;
    private final CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public DoctorServiceImpl(CrudRepositoryHelper<Doctor, DoctorRepository> doctorRepositoryHelper,
                             CrudRepositoryHelper<Patient, PatientRepository> patientRepositoryHelper,
                             DoctorRepository doctorRepository, PatientRepository patientRepository) {
        this.doctorRepositoryHelper = doctorRepositoryHelper;
        this.patientRepositoryHelper = patientRepositoryHelper;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void addPatient(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepositoryHelper.findById(doctorRepository, doctorId).get();
        Patient patient = patientRepositoryHelper.findById(patientRepository, patientId).get();
        doctor.addPatient(patient);
        doctorRepositoryHelper.update(doctorRepository, doctor);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void removePatient(Long doctorId, Long patientId) {
        Doctor doctor = doctorRepositoryHelper.findById(doctorRepository, doctorId).get();
        Patient patient = patientRepositoryHelper.findById(patientRepository, patientId).get();
        doctor.removePatient(patient);
        doctorRepositoryHelper.update(doctorRepository, doctor);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public List<Patient> getPatients(Long id) {
        return doctorRepositoryHelper.findById(doctorRepository, id).get().getPatients();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void create(Doctor entity) {
        doctorRepositoryHelper.create(doctorRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void update(Doctor entity) {
        doctorRepositoryHelper.update(doctorRepository, entity);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void delete(Long id) {
        doctorRepositoryHelper.delete(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Doctor> findById(Long id) {
        return doctorRepositoryHelper.findById(doctorRepository, id);
    }

    @Override
    @Transactional(readOnly = true)
    public DataTableResponse<Doctor> findAll(DataTableRequest request) {
        return doctorRepositoryHelper.findAll(doctorRepository, request);
    }

    @Override
    public List<Doctor> findAll() {
        return doctorRepository.findAll();
    }
}
