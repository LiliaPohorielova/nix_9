package ua.com.alevel.persistence.dao;

import ua.com.alevel.persistence.entity.Doctor;
import ua.com.alevel.persistence.entity.Patient;
import ua.com.alevel.view.dto.response.DoctorResponseDto;

import java.util.List;

public interface PatientDao extends BaseDao<Patient> {

    List<Doctor> getDoctors(Long id);

   /* void addDoctor(Long doctorId, Long patientId);

    void removeDoctor(Long doctorId, Long patientId);*/
}