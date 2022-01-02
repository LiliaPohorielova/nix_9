package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.List;

public interface DoctorService extends BaseService<Doctor> {

   List<Patient> getPatients(Long id);

    void addPatient(Long doctorId, Long patientId);

    void removePatient(Long doctorId, Long patientId);

    List<Doctor> findAll();
}