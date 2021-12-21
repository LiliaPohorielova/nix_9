package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.Declaration;

import java.util.List;

public interface DeclarationService extends BaseService<Declaration> {

    List<Declaration> findByDoctorId(Long doctorId);

    List<Declaration> findByPatientId(Long patientId);

    Declaration findByDoctorIdAndPatientId(Long patientId, Long doctorId);
}