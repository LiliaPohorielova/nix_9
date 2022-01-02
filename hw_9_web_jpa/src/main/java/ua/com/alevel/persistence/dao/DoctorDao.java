package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.view.dto.response.PatientResponseDto;

import java.util.List;

public interface DoctorDao extends BaseDao<Doctor>{

    List<Patient> getPatients(Long id);

    void addPatient(Long doctorId, Long patientId);

    void removePatient(Long doctorId, Long patientId);

    List<Doctor> findAll();
}