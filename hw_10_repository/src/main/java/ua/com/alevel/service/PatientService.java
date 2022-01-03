package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;

import java.util.List;

public interface PatientService extends BaseService<Patient> {

    List<Doctor> getDoctors(Long id);

    List<Patient> findAll();
}